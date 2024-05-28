package entity;

import java.awt.image.BufferedImage;
import main.UtilityTool;
import object.Godray;
import object.instrument.PlayerDummy;
import object.instrument.PlayerReflection;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int drawLim = 1;
    public int speed;

    public BufferedImage[] anim = new BufferedImage[2];
    public String direction = "up";
    
    public int spriteCounter = 0;
    public int attCounter = 0;
    public int spriteNum = 0;
    public int dodgeLength = 0, parryLength = 0;

    public Rectangle solidArea = new Rectangle(8, 16, 32, 32);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;
    public final int solidAreaH = solidArea.height, solidAreaW = solidArea.width, solidAreaX = solidArea.x, solidAreaY = solidArea.y;
    public boolean collisionOn = false;

    public int actionLockCounter;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public BufferedImage image;
    public String name = "NONAMEOBJ", descript = "ERROR UNPICKABLE";
    public boolean collision = false;
    public boolean pickable = true;
    public boolean destructible = false;
    public boolean openable = false;
    public boolean isPressed = false;
    public boolean overDraw = false;

    //stats
    public int hp;
    public int maxhp;

    public int endurance;
    public int maxEndurance;
    public int enduranceRegen, enduranceCount = 0;

    public int lvl;
    public int strength;
    public int dexerity;
    public int will;
    public int intellect;

    public int attack;
    public int def;
    
    public int exp;
    public int toNextLvl;

    public Entity weapon1;
    public Entity weapon2;
    public Entity armor;

    public int maxMana;
    public int mana;

    //mana
    public Projectile projectile;
    public int projectileCost;
    public int projCD = 0;

    //ammo
    public int maxAmmo;
    public int ammo;

    //equip stats
    public int dmg;
    public int protection;
    public int knockBackPower = 0;

    //Action
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int Damage = 0;
    public boolean knockBack = false;
    public int knockBackCounter = 0;
    public int defaultSpeed;
    public Entity attacker;
    public String knockbackDirection;
    public boolean isParrying = false;

    boolean attacking = false;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    boolean hpBarOn = false;
    int hpBarCounter = 0;

    //items
    public ArrayList<Entity> inv = new ArrayList<>();
    public int maxInvSize = 56;
    
    public int type, subtype;
    public final int type_player = 0, type_npc = 1, type_mob = 2, type_weapon = 3, type_armor = 4, type_consumable = 5, type_quest = 6, type_autoConsumable = 7, type_movable = 8, type_plate = 9, type_grass = 10, type_lever = 11, type_effect = 12, type_ladder = 13, type_ropeway = 14, type_mirror = 15;
    public int value;

    public boolean stackable = false;
    public int ammount = 1;
    
    //death
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;

    //path
    public boolean onPath = false;
    public boolean followPlayer = false;

    //emission
    public Entity currentLight;
    public int emissionRadius;

    //movement
    public int walkCounter = 0, randomNum = 0;
    public final int attStartRangeDef = 48;
    public int attStartRange = attStartRangeDef;
    public boolean isWalking = false, isActing = false;
    public boolean isFalling = false;

    //blinking
    float floatus = 1f, floatus0 = 0.01f;

    //cutscenes
    public boolean drawing = true;

    //ropeWay
    public int ropeWayBalance = 0;
    public boolean onRopeWay = false, isUnderTiles = false;

    //reflection
    public int yReflectionPoint;






    public Entity(GamePanel _gp){
        gp = _gp;
    }


    














    //LIVE ENTITIES
    public void setAction(){}
    public void dmgReact(){}
    public void checkDrop(){}

    public void dropItem(Entity item){
        for (int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = item;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void checkCollision(){
        collisionOn = false;
        gp.cc.checkTile(this);
        gp.cc.checkObject(this, false);
        gp.cc.checkEntity(this, gp.npc);
        gp.cc.checkEntity(this, gp.mob);
        
        boolean contactPlayer = gp.cc.checkPlayer(this);


        if(this.type == 2 && contactPlayer == true){
            damagePlayer(attack);
        }
    }



    public void update(){

        

        if(knockBack == true){
            checkCollision();
            if(collisionOn == true){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if(collisionOn == false){
                
                switch(knockbackDirection){
                    case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                }

                if(isFalling == true){
                    generatPart("gravity", this, this);
                }
            }

            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }else{
            
            checkCollision();
            setAction();
        }


            if(type == type_effect){
                
                hp--;

                if(hp == maxhp/5*4){
                    spriteNum = 1;
                }
                if(hp == maxhp/5*3){
                    spriteNum = 2;
                }
                if(hp == maxhp/5*2){
                    spriteNum = 3;
                }
                if(hp == maxhp/5){
                    spriteNum = 4;
                }
                if(hp == maxhp/6){
                    spriteNum = 5;
                }
                if(hp == 0){
                    alive = false;
                }
            }

            if(type == type_grass && spriteNum == 3 && overDraw == true){
                if(getTileDist(gp.player) > 2*gp.tileSize){
                    actionLockCounter++;
                    if(actionLockCounter == 30){
                        overDraw = false;
                        actionLockCounter = 0;
                    }
                }
            }
        
            if((type == type_player || type == type_npc || type == type_mob) && !(this instanceof Bard) && !(this instanceof PlayerDummy) && !(this instanceof PlayerReflection)){
                spriteCounter++;
                if(spriteCounter > 10) {
                    if(spriteNum == 0) {
                        spriteNum = 1;
                    }
                    else if(spriteNum == 1) {
                        spriteNum = 0;
                    }
                    spriteCounter = 0;
                }
            }

            if(type == type_mirror && spriteNum < 6){
                spriteCounter++;
                if(spriteCounter > 5) {
                    spriteNum = 5;
                }
                if(spriteCounter > 10) {
                    spriteNum = 4;
                }
                if(spriteCounter > 15) {
                    spriteNum = 0;
                }
                if(spriteCounter > 20) {
                    spriteNum = 1;
                }
                if(spriteCounter > 25) {
                    spriteNum = 2;
                }
                if(spriteCounter > 30) {
                    spriteNum = 3;
                    spriteCounter = -300;
                }
            }

            
            

            if(invincible == true){
                invincibleCounter++;
                if(invincibleCounter > 20){
                    invincible = false;
                    invincibleCounter = 0;
                }
            }

            if(projCD < 30) {
                projCD++;
            }
    		
    	}
    


    public void damagePlayer(int attack){
        if(gp.player.invincible == false && gp.player.isParrying == false){

            Damage = attack - gp.player.def;
            if(Damage < 0){
                Damage = 0;
            }

            knockBack(gp.player, this, knockBackPower);

            gp.player.hp-=Damage;
            gp.playSFX(1);
            gp.player.invincible = true;
        }

        if(gp.player.isParrying == true){
            knockBack(this, gp.player, gp.player.knockBackPower);
            generatPart("gravity", this, gp.player);
        }
    }


    

    public void knockBack(Entity target, Entity attacker, int knockBackPow){

        this.attacker = attacker;
        target.knockbackDirection = attacker.direction;
        
        switch(attacker.direction){
            case "up": target.worldY -= knockBackPow;break;
            case "down": target.worldY += knockBackPow;break;
            case "right": target.worldX += knockBackPow;break;
            case "left": target.worldX -= knockBackPow;break;}


        target.knockBack = true;
    }



    public void dyingAnim(Graphics2D g2){
        dyingCounter++;

        int j = 5;

            if(dyingCounter > j && dyingCounter <= (j*2)){
                changeAlpha(g2, 1f);
            }
            if(dyingCounter > j*2 && dyingCounter <= (j*3)){
                changeAlpha(g2, 0f);
            }
            if(dyingCounter > j*3 && dyingCounter <= (j*4)){
                changeAlpha(g2, 1f);
            }
            if(dyingCounter > j*6 && dyingCounter <= (j*5)){
                changeAlpha(g2, 0f);
            }
            if(dyingCounter > j*6){
                alive = false;
            }
    }

    

    public void speak(){
        gp.ui.currentDialog = dialogues[dialogueIndex];
        if(dialogueIndex == 2){
            dialogueIndex = 2;
        }
        else {
            dialogueIndex++;
        }

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }






    //OTHERS

    public void use(Entity entity){}
    public boolean checkForKey(Entity item){return true;}


























    public void draw(Graphics2D g2){

        BufferedImage image = null;



            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            int tempScrX = screenX;
            int tempScrY = screenY;

            if(worldX + gp.tileSize*drawLim > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize*drawLim > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                if(type != type_ropeway){
                    switch(direction) {
                        case "up":
                            if(attacking == false){
                                if(spriteNum == 0){image = anim[0];}
                                if(spriteNum == 1){image = anim[1];}
                            }
                            if(attacking == true) {
                                tempScrY = anim[0].getHeight();
                                if(spriteNum == 0){image = anim[8];}
                                if(spriteNum == 1){image = anim[9];}
                            }
                            break;
            
                        case "down":
                            if(attacking == false){
                                if(spriteNum == 0){image = anim[2];}
                                if(spriteNum == 1){image = anim[3];}
                            }
                            if(attacking == true) {
                                if(spriteNum == 0){image = anim[10];}
                                if(spriteNum == 1){image = anim[11];}
                            }
                            break;
                        case "left":
                            if(attacking == false){
                                if(spriteNum == 0){image = anim[4];}
                                if(spriteNum == 1){image = anim[5];}
                            }
                            if(attacking == true) {
                                tempScrX = screenX-anim[4].getWidth();
                                if(spriteNum == 0){image = anim[12];}
                                if(spriteNum == 1){image = anim[13];}
                            }
                            break;
                        case "right":
                            if(attacking == false){
                                if(spriteNum == 0){image = anim[6];}
                                if(spriteNum == 1){image = anim[7];}
                            }
                            if(attacking == true){
                                if(spriteNum == 0){image = anim[14];}
                                if(spriteNum == 1){image = anim[15];}
                            }
                            break;
                    }
                }
                

                //mob hpbar
                if(type == 2 && hpBarOn == true){

                    double oneScale = (double)gp.tileSize/maxhp;
                    double hpBarValue = oneScale*hp;

                    g2.setColor(Color.BLACK);
                    g2.drawRect(screenX+1, screenY - 16, gp.tileSize+2, 7);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 5);

                    hpBarCounter++;

                    if(hpBarCounter > 300){
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }

                //effects
                if(type == type_ladder){
                    if(spriteNum == 1){image = anim[1];}
                    if(spriteNum == 2){image = anim[2];}
                    if(spriteNum == 0){image = anim[0];}
                }

                //effects
                if(type == type_effect){
                    if(spriteNum == 1){image = anim[1];}
                    if(spriteNum == 2){image = anim[2];}
                    if(spriteNum == 3){image = anim[3];}
                    if(spriteNum == 4){image = anim[4];}
                    if(spriteNum == 5){image = anim[5];}
                    if(spriteNum == 0){image = anim[0];}
                }

                //mirror
                if(type == type_mirror){
                    if(spriteNum == 1){image = anim[1];}
                    if(spriteNum == 2){image = anim[2];}
                    if(spriteNum == 3){image = anim[3];}
                    if(spriteNum == 4){image = anim[4];}
                    if(spriteNum == 5){image = anim[5];}
                    if(spriteNum == 6){image = anim[6];}
                    if(spriteNum == 7){image = anim[7];}
                    if(spriteNum == 8){image = anim[8];}
                    if(spriteNum == 0){image = anim[0];}

                    tempScrX-=image.getWidth()/3;
                }

                //ropeway
                if(type == type_ropeway){
                    if(spriteNum == 1){image = anim[1];}
                    if(spriteNum == 2){image = anim[2];}
                    if(spriteNum == 3){image = anim[3];}
                    if(spriteNum == 4){image = anim[4];}
                    if(spriteNum == 0){image = anim[0];}
                }

                //boulders
                if(type == type_movable){
                    if(spriteNum == 1){image = anim[1];}
                    if(spriteNum == 2){image = anim[2];}
                    if(spriteNum == 3){image = anim[3];}
                    if(spriteNum == 0){image = anim[0];}
                }

                //levers
                if(type == type_lever){
                    if(spriteNum == 1){image = anim[1];}
                    if(spriteNum == 0){image = anim[0];}
                }

                //grass
                if(type == type_grass){
                    if(spriteNum == 1){image = anim[1];}
                    if(spriteNum == 2){image = anim[2];}
                    if(spriteNum == 3){image = anim[1];}
                    if(spriteNum == 0){image = anim[0];}
                }

                //plates
                if(type == type_plate){
                    if(isPressed == true){
                        image = anim[1];
                    }
                    else {
                        image = anim[0];
                    }
                }
                
                //bard
                if(this instanceof Bard){
                    if(spriteNum == 1){image = anim[2];}
                    if(spriteNum == 0){image = anim[3];}

                    if(spriteNum == 8){image = anim[8];}
                    if(spriteNum == 9){image = anim[9];}
                    if(spriteNum == 10){image = anim[10];}
                }

                //debug
                //collisions
                if(gp.keyH.debugMode == true){
                    g2.setColor(Color.cyan);
                    g2.drawRect(solidArea.x + screenX, solidArea.y + screenY, solidArea.width, solidArea.height);
                }
                



                
                //godray
                if(this instanceof Godray){

                    if(floatus > 0.1f){
                        floatus-=0.01f;
                        changeAlpha(g2, floatus);
                    }
                    else if(floatus0 < 0.99f){
                        floatus0+=0.01f;
                        changeAlpha(g2, floatus0);
                    }
                    if(floatus0 >= 0.99f){
                        floatus = 1f;
                        floatus0 = 0.01f;
                    }
                }

                //ANIMS
                if(invincible == true){
                    hpBarOn = true;
                    hpBarCounter = 0;
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                }

                if(dying == true){
                    dyingAnim(g2);
                }
        
                //main
                if(drawing == true){
                    g2.drawImage(image, tempScrX, tempScrY, null);
                }
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
    }








    


    

    




    public int getXdistance(Entity target){
        return Math.abs(worldX - target.worldX);
    }
    public int getYdistance(Entity target){
        return Math.abs(worldY - target.worldY);
    }
    public int getTileDist(Entity target){
        return (getXdistance(target) + getYdistance(target));
    }

    public int setTargetXAsGoal(Entity target){
        return (target.worldX + target.solidArea.x)/gp.tileSize;
    }
    public int setTargetYAsGoal(Entity target){
        return (target.worldY + target.solidArea.y)/gp.tileSize;
    }
    







    

    public Color getParticleColor(){
        Color c = null;
        return c;
    }
    public BufferedImage getPartImg(){
        BufferedImage img = null;
        return img;
    }
    public int getPartSize(){
        int size = 0;
        return size;
    }
    public int getParticleSpeed(){
        int partspeed = 0;
        return partspeed;
    }
    public int getPartMaxHp(){
        int maxLife = 0;
        return maxLife;
    }


    public void generatPart(String _type, Entity gen, Entity target){
        Color c = gen.getParticleColor();
        int size = gen.getPartSize();
        int speed = gen.getParticleSpeed();
        int maxhp = gen.getPartMaxHp();

        Particle p1 = new Particle(_type, gp, target, c, size, speed, maxhp, -1, -1);
        Particle p2 = new Particle(_type, gp, target, c, size, speed, maxhp, 1, -1);
        Particle p3 = new Particle(_type, gp, target, c, size, speed, maxhp, -1, 1);
        Particle p4 = new Particle(_type, gp, target, c, size, speed, maxhp, 1, 1);
        gp.partL.add(p1); gp.partL.add(p2); gp.partL.add(p3); gp.partL.add(p4);
    }

    public void generatPart(String _type, Entity target, Color _c, int _size, int _speed, int _maxhp, int vx, int vy){
        Color c = _c;
        int size = _size;
        int speed = _speed;
        int maxhp = _maxhp;

        if(!_type.equals("rain")){
            Particle p2 = new Particle(_type, gp, target, c, size, speed, maxhp, 1, -1, vx, vy);
            Particle p3 = new Particle(_type, gp, target, c, size, speed, maxhp, -1, 1, vx, vy);
            Particle p4 = new Particle(_type, gp, target, c, size, speed, maxhp, 1, 1, vx, vy);
            gp.partL.add(p2); gp.partL.add(p3); gp.partL.add(p4);
        }

        Particle p1 = new Particle(_type, gp, target, c, size, speed, maxhp, -1, -1, vx, vy);
        gp.partL.add(p1); 
    }

    public void generatPart(String _type, Entity target, BufferedImage _c, int _size, int _speed, int _maxhp, int vx, int vy){
        BufferedImage c = _c;
        int size = _size;
        int speed = _speed;
        int maxhp = _maxhp;

        if(!_type.equals("rain") && !_type.equals("fog")){
            Particle p2 = new Particle(_type, gp, target, c, size, speed, maxhp, 1, -1, vx, vy);
            Particle p3 = new Particle(_type, gp, target, c, size, speed, maxhp, -1, 1, vx, vy);
            Particle p4 = new Particle(_type, gp, target, c, size, speed, maxhp, 1, 1, vx, vy);
            gp.partL.add(p2); gp.partL.add(p3); gp.partL.add(p4);
        }

        Particle p1 = new Particle(_type, gp, target, c, size, speed, maxhp, -1, -1, vx, vy);
        gp.partL.add(p1); 
    }

    public void generatPart(String _type, Entity gen, Entity target, int x, int y, boolean image){

        Color c = null;
        BufferedImage img = null;

        if(image == true){
            img = gen.getPartImg();
        }
        else {
            c = gen.getParticleColor();
        }
        
        int size = gen.getPartSize();
        int speed = gen.getParticleSpeed();
        int maxhp = gen.getPartMaxHp();

        Particle p1 = new Particle(_type, gp, target, c, size, speed, maxhp, 1, -1, x, y);
        Particle p2 = new Particle(_type, gp, target, img, size, speed, maxhp, 1, -1, x, y);
        gp.partL.add(p1); gp.partL.add(p2);
    }





    






















    public void go(String _direction, int timeFrames){
            
        direction = _direction;

        if(collisionOn == false && isWalking == true){
            if(walkCounter < timeFrames){
                switch(direction){
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "right":worldX += speed;break;
                    case "left":worldX -= speed;break;}
            }

            if(walkCounter == timeFrames){
                isWalking = false;
                walkCounter = 0;
            }

            walkCounter++;
        }
    }




    public void idle(String _direction, int timeFrames){
        
        direction = _direction;

        if(walkCounter < timeFrames && isWalking == false){

        }
        
        if(walkCounter == timeFrames){
            walkCounter = 0; 
        }

        walkCounter++;
    }



    public void randomWalk(){

        actionLockCounter++;

            if(actionLockCounter == 300){

                Random random = new Random();
                randomNum = random.nextInt(100)+1;

                isWalking = true;
                actionLockCounter = 0;
            }

            if(randomNum <= 25 && randomNum > 0){
                go("up", 100);
            }
            if(randomNum <= 50 && randomNum > 25){
                go("down", 100);
            }
            if(randomNum <= 75 && randomNum > 50){
                go("left", 100);
            }
            if(randomNum <= 100 && randomNum > 75){
                go("right", 100);
            }
        
        
    }



    public boolean aggroOnPlayer(int minRadius, int maxRadius){

        boolean result = false;

        if(onPath == true && followPlayer == true){result = true;}
        

        if(getTileDist(gp.player) < minRadius*gp.tileSize && getTileDist(gp.player) > attStartRange){
            onPath = true;
            followPlayer = true;
            attacking = false;
            attCounter = 0;

            attStartRange = attStartRangeDef;
            solidArea.width = solidAreaW;
                solidArea.height = solidAreaH;
                solidArea.x = solidAreaX;
                solidArea.y = solidAreaY;
        }

        if(getTileDist(gp.player) > maxRadius*gp.tileSize){
            onPath = false;
            followPlayer = false;
        }

        if(getTileDist(gp.player) <= attStartRange){
            attacking = true;
            onPath = false;
            followPlayer = false;
        }

        if(attacking == true){
            doAnAttack();
        }
        
        return result;
    }



    public void shootProjectile(){
        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && projCD == 30){
            projectile.set(worldX, worldY, direction, true, this);
            
            for (int j = 0; j < gp.projectile[1].length; j++) {
                if(gp.projectile[gp.currentMap][j] == null){
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            projCD = 0;
        }
    }




    public void doAnAttack(){
        
        attCounter++;
        spriteCounter = 0;

        if(attCounter <= 15){
            spriteNum = 0;
        }
        if(attCounter > 20 && attCounter <= 29){
            spriteNum = 1;
        }
        if(attCounter > 29 && attCounter <= 30){
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            switch(direction){
                case "up":      solidArea.x -= solidAreaW/3; solidArea.y -= attackArea.height/2; break;
                case "down":    solidArea.x -= solidAreaW/4; break;
                case "right":   solidArea.y -= attackArea.height/2 - solidArea.y; break;
                case "left":    solidArea.y -= solidAreaY/2; solidArea.x -= solidArea.width/2; break;}

            if(gp.cc.checkPlayer(this) == true){
                damagePlayer(attack);
                attStartRange+=knockBackPower;
            }
            
        
        }
            if(attCounter > 30){
                solidArea.width = solidAreaW;
                solidArea.height = solidAreaH;
                solidArea.x = solidAreaX;
                solidArea.y = solidAreaY;

                spriteNum = 0;
                attStartRange = attStartRangeDef;
                attacking = false;

                attCounter = 0;
            }
    }





    





























    public void searchPath(int goalC, int goalR){
        int startC = (worldX + solidArea.x)/gp.tileSize;
        int startR = (worldY + solidArea.y)/gp.tileSize;

        gp.pf.setNodes(startC, startR, goalC, goalR);

        if(gp.pf.search() == true){

            int nextX = gp.pf.pl.get(0).col * gp.tileSize;
            int nextY = gp.pf.pl.get(0).row * gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }


            switch(direction){
                case "up":worldY -= speed;break;
                case "down":worldY += speed;break;
                case "right":worldX += speed;break;
                case "left":worldX -= speed;break;}




            //fin
            if(followPlayer == false){
                int nextCol = gp.pf.pl.get(0).col;
                int nextRow = gp.pf.pl.get(0).row;
    
                if(nextCol == goalC && nextRow == goalR){
                    onPath = false;
                }
            }
        }
    }








    public BufferedImage setup(String imagepath, int width, int height){
        UtilityTool ut = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            image = ut.scaleImage(image, width, height);

        } catch (IOException e) {
            
        }

        return image;
    }

    public void changeAlpha(Graphics2D g2, float aplha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, aplha));
    }
}