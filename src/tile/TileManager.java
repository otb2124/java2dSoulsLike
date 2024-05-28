package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Color;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel _gp){
        gp = _gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        setupMaps();
    }





    public void getTileImage() {

        //placeholder
        setup(0, "/res/bg/na1.png", false);

        for (int i = 1; i < 10; i++) {
            setup(i, "/res/bg/na.png", false);
        }
        
        //tiles
        setup(10, "/res/bg/brick.png", false);
        setup(11, "/res/bg/edge.png", true);

        setup(15, "/res/bg/hill0.png", "up", true);

        setup(20, "/res/bg/field.png", false);

        setup(true, 30, "/res/bg/water.png");

        setup(99, "/res/worldmaps/map01.png", false, 400, 400);

    }


    public void setupMaps(){
        loadMap("/res/maps/map01.txt", 0);
        loadMap("/res/maps/map02.txt", 1);
        loadMap("/res/maps/map03.txt", 2);
        loadMap("/res/maps/map04.txt", 3);
        loadMap("/res/maps/map05.txt", 4);
    }





    public void setup(int index, String imagepath, boolean collision){
        UtilityTool ut = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            tile[index].image = ut.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            
        }
    }
    public void setup(int index, String imagepath, String _direction0){
        UtilityTool ut = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            tile[index].image = ut.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = true;
            tile[index].direction0 = _direction0;

        } catch (IOException e) {
            
        }
    }
    public void setup(int index, String imagepath, String _direction0, boolean _isHill){
        UtilityTool ut = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            tile[index].image = ut.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = true;
            tile[index].isHill = _isHill;
            tile[index].direction0 = _direction0;

        } catch (IOException e) {
            
        }
    }
    public void setup(boolean liquid, int index, String imagepath){
        UtilityTool ut = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            tile[index].image = ut.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = false;
            tile[index].isLiquid = liquid;

        } catch (IOException e) {
            
        }
    }
    public void setup(int index, String imagepath, boolean collision, int w, int h){
        UtilityTool ut = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagepath));
            tile[index].image = ut.scaleImage(tile[index].image, w, h);
            tile[index].collision = collision;

        } catch (IOException e) {
            
        }
    }











    public void loadMap(String filePath, int mapID){

        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 1;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[mapID][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        } catch (Exception e) {
            
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){


                if(tile[tileNum].isOverDrawn == true){
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }


                //debug
                if(gp.keyH.debugMode == true && tile[tileNum].collision == true){
                    g2.setColor(new Color(0, 0, 255, 50));
                    g2.fillRect(screenX, screenY, screenX + gp.tileSize, screenY + gp.tileSize);
                }
                
            }

            
            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
        }

        public void drawUnder(Graphics2D g2){
            int worldCol = 0;
            int worldRow = 0;
    
            while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
    
                int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
    
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
    
    
                    if(tile[tileNum].isOverDrawn == false){
                        g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                    }                    
                }
    
                
                worldCol++;
                if(worldCol == gp.maxWorldCol){
                    worldCol = 0;
                    worldRow++;
                }
            }



        //debug
        if(gp.keyH.debugMode == true){
            
            g2.setColor(new Color(255, 255, 200, 100));

                for (int i = 0; i < gp.pf.pl.size(); i++) {

                    int worldX = gp.pf.pl.get(i).col * gp.tileSize;
                    int worldY = gp.pf.pl.get(i).row * gp.tileSize;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
    
                    g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                }

        }
    }
}
