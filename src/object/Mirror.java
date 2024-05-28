package object;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import entity.Entity;
import main.GamePanel;
import object.instrument.PlayerReflection;

import java.awt.Color;

public class Mirror extends Entity{

    GamePanel gp;
    boolean isSet = false;
    
    public Mirror(GamePanel _gp){
        super(_gp);
        gp = _gp;

        name = "Mirror";

        drawLim = 2;
        solidArea = new Rectangle(0, 16+20, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        type = type_mirror;
        subtype = 0;
        overDraw = false;

        anim = new BufferedImage[9];
        anim[0] = setup("/res/obj/mirror3.png", gp.tileSize*2, gp.tileSize*2);
        anim[1] = setup("/res/obj/mirror1.png", gp.tileSize*2, gp.tileSize*2);
        anim[2] = setup("/res/obj/mirror2.png", gp.tileSize*2, gp.tileSize*2);
        anim[3] = setup("/res/obj/mirror0.png", gp.tileSize*2, gp.tileSize*2);
        anim[4] = setup("/res/obj/mirror4.png", gp.tileSize*2, gp.tileSize*2);
        anim[5] = setup("/res/obj/mirror5.png", gp.tileSize*2, gp.tileSize*2);

        anim[6] = setup("/res/obj/mirror6.png", gp.tileSize*2, gp.tileSize*2);
        anim[7] = setup("/res/obj/mirror7.png", gp.tileSize*2, gp.tileSize*2);
        anim[8] = setup("/res/obj/mirror8.png", gp.tileSize*2, gp.tileSize*2);

        collision = true;
        pickable = false;
        destructible = true;
    }

    public void update() {
        super.update();

        if(getXdistance(gp.player) < anim[0].getWidth()-70 && getYdistance(gp.player) < anim[0].getWidth()+7 && spriteNum < 6){
            if(isSet == false){
                for (int i = 0; i < gp.npc[1].length; i++) {
                    if(gp.npc[gp.currentMap][i] == null){
                        gp.npc[gp.currentMap][i] = new PlayerReflection(gp);
                        gp.npc[gp.currentMap][i].worldY = worldY-20;
                        gp.npc[gp.currentMap][i].yReflectionPoint = worldY+gp.tileSize;
                        break;
                    }
                }

                isSet = true;
            }
            
        }
        else {

            if(isSet == true){
                for (int i = 0; i < gp.npc[1].length; i++) {
                    if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i] instanceof PlayerReflection){
                        gp.npc[gp.currentMap][i] = null;
                    }
                }

                isSet = false;
            }
            
        }
        

    }

    public Color getParticleColor(){
        Color c = Color.darkGray;
        return c;
    }
    public int getPartSize(){
        int _size = 6;
        return _size;
    }
    public int getParticleSpeed(){
        int partspeed = 2;
        return partspeed;
    }
    public int getPartMaxHp(){
        int maxLife = 15;
        return maxLife;
    }
}
