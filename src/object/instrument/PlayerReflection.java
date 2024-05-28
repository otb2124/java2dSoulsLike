package object.instrument;

import entity.Entity;
import main.GamePanel;
import java.awt.image.BufferedImage;

public class PlayerReflection extends Entity{

    GamePanel gp;
    
    public PlayerReflection(GamePanel _gp){
        super(_gp);
        gp = _gp;
        type = type_npc;
        overDraw = false;
        collision = false;
        anim = new BufferedImage[8];
        getImage();
    }

    public void getImage(){
        anim[0] = setup("/res/player/player_up_r.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/player/player_up_l.png", gp.tileSize, gp.tileSize);

        anim[2] = setup("/res/player/player_down_l.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/player/player_down_r.png", gp.tileSize, gp.tileSize);

        anim[4] = setup("/res/player/player_left.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/player/player_left2.png", gp.tileSize, gp.tileSize);

        anim[6] = setup("/res/player/player_right.png", gp.tileSize, gp.tileSize);
        anim[7] = setup("/res/player/player_right2.png", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        
            worldX = gp.player.worldX;
            if(gp.player.worldY > yReflectionPoint){
                drawing = true;
                worldY = yReflectionPoint - (gp.player.worldY - yReflectionPoint);
            }
            else{
                drawing = false;
                worldY = yReflectionPoint - (gp.player.worldY - yReflectionPoint);
            }

        

        switch(gp.player.direction){
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "left"; break;
            case "right": direction = "right"; break;
        }

        spriteNum = gp.player.spriteNum;
    }

}
