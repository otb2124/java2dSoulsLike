package main;

import java.awt.Color;

import entity.Entity;
import object.instrument.PlayerDummy;

public class CutsceneManager {
    
    GamePanel gp;
    int scenePhase = 0;
    int counter = 0, counter2 = 0;
    public int cutsceneNum = -1;
    int dummyID = -1;

    public CutsceneManager(GamePanel _gp){
        gp = _gp;
    }


    public void update(){

        //cutsenes
        if(cutsceneNum != -1){
            switch(cutsceneNum){
                case 0: 
                counter2++;
                setCameraTo("right", 360, 1); 
                if(counter2 == 400){
                    playDialogue(5, gp.npc[gp.currentMap][0]); 
                }
                if(counter2 == 900){
                    end();
                    counter2 = 0;
                }
                
            }
        }






        //fallDeath cutscene
        if(gp.player.fallDeathAnim == true && gp.gameState != gp.deathState){
            if(scenePhase == 0){
                for (int i = 0; i < gp.npc[1].length; i++) {
                    if(gp.npc[gp.currentMap][i] == null){
                        gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                        gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                        gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                        gp.npc[gp.currentMap][i].direction = gp.player.direction;
                        dummyID = i;
                        break;
                    }
                }
    
                scenePhase++;
            }
            if(scenePhase == 1){
                gp.player.drawing = false;
                gp.npc[gp.currentMap][dummyID].worldY+=10;
                gp.npc[gp.currentMap][dummyID].isUnderTiles = true;
                gp.tm.tile[0].isOverDrawn = false;
                

                if(gp.npc[gp.currentMap][dummyID].worldY >+ gp.player.worldY+400){
                    gp.npc[gp.currentMap][dummyID] = null;
                    gp.gameState = gp.deathState;
                    scenePhase = 0;
                }
            }
        }
    }






    public void playDialogue(){
        
    }


    public void setCameraTo(String direction, int time, int speed){
        if(scenePhase == 0){
            for (int i = 0; i < gp.npc[1].length; i++) {
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    dummyID = i;
                    break;
                }
            }

            scenePhase++;
        }
        else if(scenePhase == 1){
            counter++;
            
            gp.em.bleak.summonBleak(new Color(80, 0, 0), time*2+time/2, false);
            gp.player.drawing = false;
            gp.player.invincible = true;


            switch(direction){
                case "up": gp.player.worldY-=speed; break;
                case "down": gp.player.worldY+=speed; break;
                case "left": gp.player.worldX-=speed; break;
                case "right": gp.player.worldX+=speed; break;
            }
            
            if(counter == time){
                counter = 0;
                scenePhase++;
            }
        }

    }

    public void playDialogue(int id, Entity e){
        e.speak();
    }


    public void end(){
        gp.player.worldX = gp.npc[gp.currentMap][dummyID].worldX;
        gp.player.worldY = gp.npc[gp.currentMap][dummyID].worldY;
        gp.player.direction = gp.npc[gp.currentMap][dummyID].direction;
        gp.npc[gp.currentMap][dummyID] = null;
        dummyID = -1;
        gp.player.drawing = true;
        gp.gameState = gp.playState;
        cutsceneNum = -1;
        scenePhase = 0;
    }
}
