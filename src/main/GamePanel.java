package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import ai.Pathfinder;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import object.instrument.PlayerDummy;
import tile.TileManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import tile.Map;
import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;


public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETS
    final int originalTileSize = 16; //16x16

    public final int Scale = 3;

    public final int tileSize = originalTileSize*Scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 9;
    public final int screenWidth = maxScreenCol*tileSize;
    public final int screenHeigth = maxScreenRow*tileSize;

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

    //fullscr
    public int screenWidth2 = screenWidth;
    public int screenHeigth2 = screenHeigth;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //FPS
    int FPS = 60;
    public long timer;
    public int fpsCount;

    public TileManager tm = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound ost = new Sound();
    Sound sfx = new Sound();
    public CollisionChecker cc = new CollisionChecker(this);
    public AssetSetter as = new AssetSetter(this);
    public UI ui =new UI(this);
    public EventHandler eh = new EventHandler(this);
    public Pathfinder pf = new Pathfinder(this);
    public EnvironmentManager em = new EnvironmentManager(this);
    Map worldMap = new Map(this);
    public Config config = new Config(this);
    public CutsceneManager cm = new CutsceneManager(this);

    //entities
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][100]; //at same time
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity mob[][] = new Entity[maxMap][10];
    public Entity instrument[][] = new Entity[maxMap][50];
    public ArrayList<Entity> partL = new ArrayList<>();
    public Entity projectile[][] = new Entity[maxMap][20];

    public ArrayList<Entity> el = new ArrayList<>();

    Thread gameThread;


    //Player defaults
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 15;

    //game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int deathState = 4;
    public final int tradeState = 5;
    public final int mapState = 6;
    public final int cutsceneState = 7;
    public final int titleState = 0;
    

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame(){
        as.setObject();
        as.setNPC();
        as.setMob();
        em.setup();
        gameState = playState;

        tempScreen = new BufferedImage(screenWidth, screenHeigth, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        if(fullScreenOn == true){
            setToFullSCreen();
        }
    }


    public void run() {
    	
    	double drawInterwal = 1000000000/FPS;
    	double delta = 0;
    	long lastTime = System.nanoTime();
    	long currentTime;
    	timer = 0;
    	int drawCount = 0;
    	
        while(gameThread != null) {
        	
        	currentTime = System.nanoTime();
        	
        	delta += (currentTime - lastTime) / drawInterwal;
        	timer += (currentTime - lastTime);
        	
        	lastTime = currentTime;
        	
        	if(delta >= 1) {
        		update();
                drawToTemp();
                drawToScreen();
            	delta--;
            	drawCount++;
        	}
        	
        	if(timer >= 1000000000) {
                if(keyH.debugMode == true){
                    fpsCount = drawCount;
                }
                drawCount = 0;
        		timer = 0;
        	}
        	
        }
        
    }


    public void update(){
        
        if(gameState == playState  || gameState == pauseState || gameState == deathState || gameState == mapState || gameState == cutsceneState){

            player.update();

            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }

            //effects, grass3
            for(int i = 0; i < obj[1].length; i++){
                if(obj[currentMap][i] != null && (obj[currentMap][i].type == obj[currentMap][i].type_effect || obj[currentMap][i].type == obj[currentMap][i].type_mirror || (obj[currentMap][i].type == obj[currentMap][i].type_grass && obj[currentMap][i].spriteNum == 3))){
                    obj[currentMap][i].update();
                }

                if(obj[currentMap][i] != null && obj[currentMap][i].alive == false){
                    obj[currentMap][i] = null;
                }
            }


            for(int i = 0; i < mob[1].length; i++){
                if(mob[currentMap][i] != null){
                    if(mob[currentMap][i].alive == true && mob[currentMap][i].dying == false){
                        mob[currentMap][i].update();
                    }
                    if(mob[currentMap][i].alive == false){
                        mob[currentMap][i].checkDrop();
                        mob[currentMap][i] = null;
                    }
                }
            }
            for(int i = 0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null){
                    if(projectile[currentMap][i].alive == true){
                        projectile[currentMap][i].update();
                    }
                    if(projectile[currentMap][i].alive == false){
                        projectile[currentMap][i] = null;
                    }
                }
            }
            for(int i = 0; i < partL.size(); i++){
                if(partL.get(i) != null){
                    if(partL.get(i).alive == true){
                        partL.get(i).update();
                    }
                    if(partL.get(i).alive == false){
                        partL.remove(i);
                    }
                }
            }


            
            
            em.update();
            eh.update();
            cm.update();
            
            

        }
        if(gameState == pauseState){
            player.direction = "down";
        }
        
    }




    public void drawToTemp(){

        //DEBUG
        long drawStart = 0;

        if(keyH.debugMode == true){
            drawStart = System.nanoTime();
        }
    


        //TITLE
        if(gameState == titleState){
            ui.draw(g2);
        }
        else{

            if(player.fallDeathAnim == true){
                tm.drawUnder(g2);
                for(int i = 0; i < npc[1].length; i++){
                    if(npc[currentMap][i] != null) {
                        if(npc[currentMap][i] instanceof PlayerDummy){
                            npc[currentMap][i].draw(g2);
                        }
                    }
                }
            }


            //Tiles
            tm.draw(g2);



            //ENTITIES
            
                


                //mobs
                for(int i = 0; i < mob[1].length; i++){
                    if(mob[currentMap][i] != null) {
                        el.add(mob[currentMap][i]);
                    }
                }

                //projectiles
                for(int i = 0; i < projectile[1].length; i++){
                    if(projectile[currentMap][i] != null) {
                        el.add(projectile[currentMap][i]);
                    }
                }

                //obj
                for(int i = 0; i < obj[1].length; i++){
                    if(obj[currentMap][i] != null && obj[currentMap][i].overDraw == false) {
                        el.add(obj[currentMap][i]);
                    }
                }

                if(em.bleak.overDraw == false){
                    em.bleak.draw(g2);
                }

                //npcs
                for(int i = 0; i < npc[1].length; i++){
                    if(npc[currentMap][i] != null) {

                        if(!(npc[currentMap][i] instanceof PlayerDummy)){
                            el.add(npc[currentMap][i]);
                        }
                        else{
                            if(player.fallDeathAnim == false){
                                el.add(npc[currentMap][i]);
                            }
                        }


                        
                    }
                }
                

                //player
                el.add(player);



                //SORT
                Collections.sort(el, new Comparator<Entity>() {
                    public int compare(Entity e1, Entity e2){

                        int result = Integer.compare(e1.worldY, e2.worldY);
                        return result;
                    }
                });


                //obj over
                for(int i = 0; i < obj[1].length; i++){
                    if(obj[currentMap][i] != null && obj[currentMap][i].overDraw == true) {
                        el.add(obj[currentMap][i]);
                    }
                }

                //draw entiies
                for(int i = 0; i < el.size(); i++){
                    el.get(i).draw(g2);
                }

                //particles
                for(int i = 0; i < partL.size(); i++){
                    if(partL.get(i) != null) {
                        partL.get(i).draw(g2);
                    }
                }
                
                //clear el
                el.clear();


            //ENVI
            em.draw(g2);
            if(em.bleak.overDraw == true){
                em.bleak.draw(g2);
            }



            //UI
            ui.draw(g2);
        }


        //DEBUG
        if(keyH.debugMode == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            player.hp = player.maxhp;
            player.mana = player.maxMana;

            g2.setColor(Color.white);
            g2.setFont(getFont().deriveFont(Font.PLAIN));
            g2.drawString("Inv Frames: " + player.invincibleCounter, tileSize, tileSize*5+tileSize/2);
            g2.drawString("DrawTime: " + passed, tileSize, tileSize*6);
            g2.drawString("FPS: " + fpsCount, tileSize, tileSize*6+tileSize/2);
            g2.drawString("In-game day:" + em.light.date, tileSize, tileSize*7);
            g2.drawString("DayTime:" + em.light.dayState, tileSize, tileSize*7 + tileSize/2);
            g2.drawString("X:" + player.worldX/tileSize, tileSize, tileSize*8);
            g2.drawString("Y:" + player.worldY/tileSize, tileSize*2, tileSize*8);
            g2.drawString("CurrentMap:" + currentMap, tileSize, tileSize*8+tileSize/2);

            g2.setColor(Color.red);
            g2.drawString(player.Damage + "", player.screenX + player.solidArea.x, player.screenY-tileSize/3);
            for (int i = 0; i < mob[1].length; i++) {
                if(mob[currentMap][i] != null){
                    g2.setColor(Color.green);
                g2.drawString(mob[currentMap][i].Damage + "", player.screenX+player.solidArea.width, player.screenY-tileSize/3);
                }
            }
            

            g2.setColor(Color.green);
            g2.drawRect(player.solidArea.x + player.screenX, player.solidArea.y + player.screenY, player.solidArea.width, player.solidArea.height);

        }
    }

    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeigth2, null);
        g.dispose();
    }

    public void setToFullSCreen(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        screenWidth2 = Main.window.getWidth();
        screenHeigth2 = Main.window.getHeight();
    }
    

    public void playMusic(int i){
        ost.setFile(i);
        ost.play();
        ost.loop();
    }
    public void stopMusic() {
        ost.stop();
    }
    public void playSFX(int i){
        sfx.setFile(i);
        sfx.play();
    }
}

