package main;

import object.effects.Explosion;

public class EventHandler {

    GamePanel gp;
    EventRect er[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    boolean lock[] = new boolean[10];
    int counter[] = new int[10];

    public EventHandler(GamePanel _gp){
        gp = _gp;

        for (int i = 0; i < lock.length; i++) {
            lock[i] = false;
            counter[i] = 0;
        }

        er = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow && map < gp.maxMap){
            er[map][col][row] = new EventRect();
            er[map][col][row].x = 23;
            er[map][col][row].y = 23;
            er[map][col][row].width = 2;
            er[map][col][row].height = 2;
            er[map][col][row].eventReactDefaultX = er[map][col][row].x;
            er[map][col][row].eventReactDefaultY = er[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }

        
    }

    public void checkEvent() {
        
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if(canTouchEvent == true){
            if(hit(0, 3, 18, "left") == true) {teleportPlayer(gp.playState, 10, 18);}
            if(hit(0, 2, 14, "any") == true) {damagePit(gp.playState, true);}

            if(hit(0, 3, 19, "any") == true) {changeMap(1, 10, 18);}
            if(hit(0, 3, 30, "any") == true) {changeMap(2, 16, 16);}
            if(hit(0, 3, 32, "any") == true) {changeMap(3, 10, 10);}
            if(hit(0, 3, 34, "any") == true) {changeMap(4, 25, 45);}

            if(hit(1, 10, 19, "any") == true) {changeMap(0, 10, 18);}
            if(hit(2, 16, 19, "any") == true) {changeMap(0, 16, 16);}
            if(hit(3, 10, 11, "any") == true) {changeMap(0, 10, 10);}

            
        }

        if(lock[3] == false){
            if(hit(0, 2, 14, "any") == true) {startCutscene(0); lock[3] = true;}
        }




        if(lock[0] == false){
            if(hit(0, 2, 36, "any") == true) {
                lock[1] = true;
            }
        }

        if(gp.currentMap == 0){
            if(hit(15, 0, 25, 5, "any")){ if(lock[2] == false){gp.obj[gp.currentMap][14].generatPart("gravity", gp.obj[gp.currentMap][14], gp.obj[gp.currentMap][14]); gp.obj[gp.currentMap][14].isPressed = true;} lock[2] = true;} else {lock[2] = false; gp.obj[gp.currentMap][14].isPressed = false;}
        }
        
        
    }



    public void update(){
        if(lock[1] == true) {
            counter[0]++;
            lock[0] = true;

            if(counter[0] == 1){
                gp.as.setEffect(new Explosion(gp), 2, 36); 
                gp.playSFX(0);
            }
            if(counter[0] == 10){
                gp.as.setEffect(new Explosion(gp), 1, 36); 
                gp.playSFX(0);
            }
            if(counter[0] == 20){
                gp.as.setEffect(new Explosion(gp), 3, 36); 
                gp.playSFX(0);
            }
            if(counter[0] == 30){
                gp.as.setEffect(new Explosion(gp), 4, 36);
                gp.playSFX(0);
            }
            if(counter[0] == 31){
                counter[0] = 0;
                lock[0] = false;
                lock[1] = false;
            }    
        }
    }

    public boolean hit(int map, int col, int row, String direction){

        boolean hit = false;

        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            er[map][col][row].x = col*gp.tileSize + er[map][col][row].x;
            er[map][col][row].y = row*gp.tileSize + er[map][col][row].y;

            if(gp.player.solidArea.intersects(er[map][col][row]) && er[map][col][row].eventDone == false){
                if(gp.player.direction.contentEquals(direction) || direction.contentEquals("any")){
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            er[map][col][row].x = er[map][col][row].eventReactDefaultX;
            er[map][col][row].y = er[map][col][row].eventReactDefaultY;
        }

        return hit;
    }

    public boolean hit(int entityId, int map, int col, int row, String direction){

            boolean hit = false;
    
            if(map == gp.currentMap){
                gp.obj[map][entityId].solidArea.x = gp.obj[map][entityId].worldX + gp.obj[map][entityId].solidArea.x;
                gp.obj[map][entityId].solidArea.y = gp.obj[map][entityId].worldY + gp.obj[map][entityId].solidArea.y;
                er[map][col][row].x = col*gp.tileSize + er[map][col][row].x;
                er[map][col][row].y = row*gp.tileSize + er[map][col][row].y;
    
                if(gp.obj[map][entityId].solidArea.intersects(er[map][col][row]) && er[map][col][row].eventDone == false){
                    if(gp.obj[map][entityId].direction.contentEquals(direction) || direction.contentEquals("any")){
                        hit = true;
    
                        previousEventX = gp.player.worldX;
                        previousEventY = gp.player.worldY;
                    }
                }
    
                gp.obj[map][entityId].solidArea.x = gp.obj[map][entityId].solidAreaDefaultX;
                gp.obj[map][entityId].solidArea.y = gp.obj[map][entityId].solidAreaDefaultY;
                er[map][col][row].x = er[map][col][row].eventReactDefaultX;
                er[map][col][row].y = er[map][col][row].eventReactDefaultY;
            }

        

            return hit;
    }


    public void damagePit(int gamestate,boolean consistent) {
        gp.gameState = gamestate;
        gp.player.hp -= 1;

        if(consistent == true){
            
        }
        else {
            canTouchEvent = false;
        }
    }
    public void healObject(int gamestate) {
        if(gp.keyH.dialoguePressed == true){
            gp.gameState = gamestate;
            gp.player.hp = gp.player.maxhp;

            gp.as.setMob();
        }
    }
    public void teleportPlayer(int gamestate, int x, int y) {
        if(gp.keyH.dialoguePressed == true){
            gp.gameState = gamestate;
            gp.player.worldX = x*gp.tileSize;
            gp.player.worldY = y*gp.tileSize;
        }
    }

    public void changeMap(int map, int col, int row) {
            gp.currentMap = map;
            gp.player.worldX = gp.tileSize*col;
            gp.player.worldY = gp.tileSize*row;

            previousEventX = gp.player.worldX;
            previousEventY = gp.player.worldY;

            gp.em.rain.changed = false;
        
    }

    public void startCutscene(int id){
        gp.gameState = gp.cutsceneState;
        gp.cm.cutsceneNum = id;
    }
}
