package tile;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map extends TileManager{

    GamePanel gp;
    BufferedImage worldMap[];

    public Map(GamePanel _gp) {
        super(_gp);
        gp = _gp;
        createWorldMap();
    }
    
    public void createWorldMap(){
        worldMap = new BufferedImage[gp.maxMap];
        
        int worldMapW = gp.tileSize * gp.maxWorldCol;
        int worldMapH = gp.tileSize * gp.maxWorldRow;

        for (int i = 0; i < gp.maxMap; i++) {
            
            worldMap[i] = new BufferedImage(worldMapW, worldMapH, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                int tileNum = mapTileNum[i][col][row];
                int x = gp.tileSize * col;
                int y = gp.tileSize * row;

                //map img
                g2.drawImage(tile[tileNum].image, x, y, null);

                col++;
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            g2.dispose();
        }
    }

        public void drawFullMap(Graphics2D g2){

            //bg
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);


            //draw map
            int w = 400;
            int h = 400;
            int x = gp.screenWidth/2 - w/2;
            int y = gp.screenHeigth/2 - h/2;

            if(gp.currentMap == 0){
                g2.drawImage(worldMap[gp.currentMap], x, y, w, h, null);
            }
            else {
                g2.drawImage(tile[99].image, x, y, w, h, null);
            }




            //player
            double scale = (double)(gp.tileSize * gp.maxWorldCol)/w;
            int playerX = (int)(x + gp.player.worldX/scale);
            int playerY = (int)(y + gp.player.worldY/scale);
            int playerSize = (int)(gp.tileSize/scale);
            g2.drawImage(gp.player.anim[3], playerX, playerY, playerSize, playerSize, null);


            //loc name
            String loc = "null";
            switch(gp.currentMap){
                case 0: loc = "Home"; break;
                case 1: loc = "Merchant's Quarters"; break;
            }
            g2.setFont(gp.ui.chiller.deriveFont(30F));
            g2.setColor(Color.white);
            g2.drawString(loc, x, y);
        }
    
}
