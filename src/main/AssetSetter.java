package main;

import entity.Bard;
import entity.Entity;
import entity.Merchant;
import entity.mob.Knight;
import entity.mob.Orc;
import entity.mob.Slime;
import object.Heart;
import object.Stone;
import object.Chest;
import object.Door;
import object.Godray;
import object.Key;
import object.Ladder;
import object.Lantern;
import object.Lever;
import object.Mirror;
import object.Plate;
import object.Puzzle_Stone;
import object.Ropeway;
import object.Scythe;
import object.Shield_Stone;
import object.Spike;
import object.TPStone;
import object.Tent;
import object.Tree;
import object.Wheat;
import object.instrument.PlayerReflection;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel _gp){
        gp = _gp;
    }

    public void setObject(){

        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new Key(gp);
        gp.obj[mapNum][i].worldX = 5* gp.tileSize;
        gp.obj[mapNum][i].worldY = 5*gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new Chest(gp);
        gp.obj[mapNum][i].worldX = 22* gp.tileSize;
        gp.obj[mapNum][i].worldY = 15*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Door(gp);
        gp.obj[mapNum][i].worldX = 1* gp.tileSize;
        gp.obj[mapNum][i].worldY = 1*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Spike(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 14*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Heart(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 16*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new TPStone(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 18*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Scythe(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 20*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Shield_Stone(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 21*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Stone(gp);
        gp.obj[mapNum][i].worldX = 21* gp.tileSize;
        gp.obj[mapNum][i].worldY = 5*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Stone(gp);
        gp.obj[mapNum][i].worldX = 22* gp.tileSize;
        gp.obj[mapNum][i].worldY = 5*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Stone(gp);
        gp.obj[mapNum][i].worldX = 23* gp.tileSize;
        gp.obj[mapNum][i].worldY = 5*gp.tileSize;

        gp.obj[mapNum][i] = new Spike(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 19*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Spike(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 30*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Spike(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 32*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Spike(gp);
        gp.obj[mapNum][i].worldX = 2* gp.tileSize;
        gp.obj[mapNum][i].worldY = 34*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Plate(gp);
        gp.obj[mapNum][i].worldX = 25* gp.tileSize;
        gp.obj[mapNum][i].worldY = 5*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Puzzle_Stone(gp);
        gp.obj[mapNum][i].worldX = 30* gp.tileSize;
        gp.obj[mapNum][i].worldY = 5*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Wheat(gp);
        gp.obj[mapNum][i].worldX = 42* gp.tileSize;
        gp.obj[mapNum][i].worldY = 3*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Wheat(gp);
        gp.obj[mapNum][i].worldX = 43* gp.tileSize;
        gp.obj[mapNum][i].worldY = 3*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Wheat(gp);
        gp.obj[mapNum][i].worldX = 44* gp.tileSize;
        gp.obj[mapNum][i].worldY = 3*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Wheat(gp);
        gp.obj[mapNum][i].worldX = 42* gp.tileSize;
        gp.obj[mapNum][i].worldY = 4*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Wheat(gp);
        gp.obj[mapNum][i].worldX = 43* gp.tileSize;
        gp.obj[mapNum][i].worldY = 4*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Wheat(gp);
        gp.obj[mapNum][i].worldX = 44* gp.tileSize;
        gp.obj[mapNum][i].worldY = 4*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Lever(gp);
        gp.obj[mapNum][i].worldX = 30* gp.tileSize;
        gp.obj[mapNum][i].worldY = 10*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Tree(gp);
        gp.obj[mapNum][i].worldX = 10* gp.tileSize;
        gp.obj[mapNum][i].worldY = 10*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Godray(gp);
        gp.obj[mapNum][i].worldX = 10* gp.tileSize;
        gp.obj[mapNum][i].worldY = 8*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Ladder(gp);
        gp.obj[mapNum][i].worldX = 35* gp.tileSize;
        gp.obj[mapNum][i].worldY = 13*gp.tileSize;
        gp.obj[mapNum][i].spriteNum = 2;
        i++;

        gp.obj[mapNum][i] = new Mirror(gp);
        gp.obj[mapNum][i].worldX = 30* gp.tileSize;
        gp.obj[mapNum][i].worldY = 30*gp.tileSize;
        i++;








        mapNum = 1;
        i = 0;
        gp.obj[mapNum][i] = new Lantern(gp);
        gp.obj[mapNum][i].worldX = 23* gp.tileSize;
        gp.obj[mapNum][i].worldY = 5*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Tent(gp);
        gp.obj[mapNum][i].worldX = 23* gp.tileSize;
        gp.obj[mapNum][i].worldY = 8*gp.tileSize;
        i++;






        mapNum = 2;
        i = 0;

        //sticks
        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 16* gp.tileSize;
        gp.obj[mapNum][i].worldY = 12*gp.tileSize;
        gp.obj[mapNum][i].direction = "down"; 
        i++;

        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 16* gp.tileSize;
        gp.obj[mapNum][i].worldY = 7*gp.tileSize;
        gp.obj[mapNum][i].direction = "up"; 
        i++;


        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 17* gp.tileSize;
        gp.obj[mapNum][i].worldY = 6*gp.tileSize;
        gp.obj[mapNum][i].direction = "down"; 
        i++;

        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 21* gp.tileSize;
        gp.obj[mapNum][i].worldY = 3*gp.tileSize;
        gp.obj[mapNum][i].direction = "down"; 
        i++;



        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 22* gp.tileSize;
        gp.obj[mapNum][i].worldY = 4*gp.tileSize;
        gp.obj[mapNum][i].direction = "up"; 
        i++;

        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 26* gp.tileSize;
        gp.obj[mapNum][i].worldY = 8*gp.tileSize;
        gp.obj[mapNum][i].direction = "right"; 
        i++;

        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 26* gp.tileSize;
        gp.obj[mapNum][i].worldY = 9*gp.tileSize;
        gp.obj[mapNum][i].direction = "right"; 
        i++;

        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 26* gp.tileSize;
        gp.obj[mapNum][i].worldY = 14*gp.tileSize;
        gp.obj[mapNum][i].direction = "down"; 
        i++;

        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 25* gp.tileSize;
        gp.obj[mapNum][i].worldY = 15*gp.tileSize;
        gp.obj[mapNum][i].direction = "up";  
        i++;

        gp.obj[mapNum][i] = new Ropeway(gp);
        gp.obj[mapNum][i].worldX = 19* gp.tileSize;
        gp.obj[mapNum][i].worldY = 15*gp.tileSize;
        gp.obj[mapNum][i].direction = "left"; 
        i++;

        //ropes
        for (int j = 8; j < 12; j++) {
            gp.obj[mapNum][i] = new Ropeway(gp);
            gp.obj[mapNum][i].worldX = 16*gp.tileSize;
            gp.obj[mapNum][i].worldY = j*gp.tileSize;
            gp.obj[mapNum][i].spriteNum = 1;
            gp.obj[mapNum][i].collision = false;

            i++;
        }

        int l = 0;
        for (int j = 4; j < 7; j++) {
            gp.obj[mapNum][i] = new Ropeway(gp);
            gp.obj[mapNum][i].worldX = (20-l)*gp.tileSize;
            gp.obj[mapNum][i].worldY = j*gp.tileSize-20;
            gp.obj[mapNum][i].spriteNum = 3;
            gp.obj[mapNum][i].collision = false;
            i++;
            l++;
        }

        l = 0;
        for (int j = 5; j < 8; j++) {
            gp.obj[mapNum][i] = new Ropeway(gp);
            gp.obj[mapNum][i].worldX = (23+l)*gp.tileSize;
            gp.obj[mapNum][i].worldY = j*gp.tileSize;
            gp.obj[mapNum][i].spriteNum = 4;
            gp.obj[mapNum][i].collision = false;
            i++;
            l++;
        }

        for (int j = 10; j < 14; j++) {
            gp.obj[mapNum][i] = new Ropeway(gp);
            gp.obj[mapNum][i].worldX = 26*gp.tileSize;
            gp.obj[mapNum][i].worldY = j*gp.tileSize;
            gp.obj[mapNum][i].spriteNum = 1;
            gp.obj[mapNum][i].collision = false;
            i++;
        }

        for (int j = 20; j < 25; j++) {
            gp.obj[mapNum][i] = new Ropeway(gp);
            gp.obj[mapNum][i].worldX = j*gp.tileSize;
            gp.obj[mapNum][i].worldY = 15*gp.tileSize;
            gp.obj[mapNum][i].spriteNum = 2;
            gp.obj[mapNum][i].collision = false;
            i++;
        }







        mapNum = 4;
        i = 0;

        for (int j = 30; j < 40; j++) {
            for (int k = 30; k < 40; k++) {
                gp.obj[mapNum][i] = new Wheat(gp);
                gp.obj[mapNum][i].worldX = k*gp.tileSize;
                gp.obj[mapNum][i].worldY = j*gp.tileSize;
                if(j == 38){
                    gp.obj[mapNum][i].overDraw = false;
                    gp.obj[mapNum][i].worldY = j*gp.tileSize-gp.tileSize/2;
                }
                if(j == 39){
                    gp.obj[mapNum][i].overDraw = false;
                    gp.obj[mapNum][i].spriteNum = 3;
                    gp.obj[mapNum][i].worldY = j*gp.tileSize-gp.tileSize*3/2-5;
                    gp.obj[mapNum][i].solidAreaDefaultY-=10;
                }
                i++;
            }
        }
        
    }

    public void setNPC(){
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new Bard(gp);
        gp.npc[mapNum][i].worldX = 10* gp.tileSize;
        gp.npc[mapNum][i].worldY = 14*gp.tileSize;
        i++;

        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new Merchant(gp);
        gp.npc[mapNum][i].worldX = 10* gp.tileSize;
        gp.npc[mapNum][i].worldY = 14*gp.tileSize;
    }

    public void setMob(){
        int mapNum = 0;
        int i = 0;

        gp.mob[mapNum][i] = new Slime(gp);
        gp.mob[mapNum][i].worldX = 4*gp.tileSize;
        gp.mob[mapNum][i].worldY = 4*gp.tileSize;
        i++;

        gp.mob[mapNum][i] = new Slime(gp);
        gp.mob[mapNum][i].worldX = 5*gp.tileSize;
        gp.mob[mapNum][i].worldY = 4*gp.tileSize;
        i++;

        gp.mob[mapNum][i] = new Slime(gp);
        gp.mob[mapNum][i].worldX = 6*gp.tileSize;
        gp.mob[mapNum][i].worldY = 4*gp.tileSize;
        i++;

        gp.mob[mapNum][i] = new Knight(gp);
        gp.mob[mapNum][i].worldX = 40*gp.tileSize;
        gp.mob[mapNum][i].worldY = 40*gp.tileSize;

        mapNum = 1;
        i = 0;
        gp.mob[mapNum][i] = new Orc(gp);
        gp.mob[mapNum][i].worldX = 4*gp.tileSize;
        gp.mob[mapNum][i].worldY = 4*gp.tileSize;
    }







public void setEffect(Entity item, int wX, int wY){

        for (int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = item;
                gp.obj[gp.currentMap][i].worldX = wX*gp.tileSize;
                gp.obj[gp.currentMap][i].worldY = wY*gp.tileSize;
                break;
            }
        }


        
    
        

    }



public void setLadder(int id){

        int mapNum = 0;
        int i = 30;

        gp.obj[mapNum][i] = new Ladder(gp);
        gp.obj[mapNum][i].worldX = 35* gp.tileSize;
        gp.obj[mapNum][i].worldY = 14*gp.tileSize;
        gp.obj[mapNum][i].spriteNum = 1;
        gp.obj[mapNum][i].collision = false;
        i++;

        gp.obj[mapNum][i] = new Ladder(gp);
        gp.obj[mapNum][i].worldX = 35* gp.tileSize;
        gp.obj[mapNum][i].worldY = 15*gp.tileSize;
        gp.obj[mapNum][i].spriteNum = 1;
        gp.obj[mapNum][i].collision = false;
        i++;

        gp.obj[mapNum][i] = new Ladder(gp);
        gp.obj[mapNum][i].worldX = 35* gp.tileSize;
        gp.obj[mapNum][i].worldY = 16*gp.tileSize;
        gp.obj[mapNum][i].spriteNum = 1;
        gp.obj[mapNum][i].collision = false;
        i++;

        gp.obj[mapNum][i] = new Ladder(gp);
        gp.obj[mapNum][i].worldX = 35* gp.tileSize;
        gp.obj[mapNum][i].worldY = 17*gp.tileSize;
        gp.obj[mapNum][i].spriteNum = 1;
        gp.obj[mapNum][i].collision = false;
        i++;

        gp.obj[mapNum][i] = new Ladder(gp);
        gp.obj[mapNum][i].worldX = 35* gp.tileSize;
        gp.obj[mapNum][i].worldY = 18*gp.tileSize;
        gp.obj[mapNum][i].spriteNum = 0;
}


public void deleteLadder(int id){
        int mapNum = 0;
        int i = 30;

        gp.obj[mapNum][i] = null;
        i++;
        gp.obj[mapNum][i] = null;
        i++;
        gp.obj[mapNum][i] = null;
        i++;
        gp.obj[mapNum][i] = null;
        i++;
        gp.obj[mapNum][i] = null;
}
}