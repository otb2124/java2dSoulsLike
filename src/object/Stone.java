package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

public class Stone extends Entity{

    GamePanel gp;

    public Stone(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        anim = new BufferedImage[2];
        anim[0] = setup("/res/obj/stone.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/stone1.png", gp.tileSize, gp.tileSize);

        pickable = false;
        collision = true;
        destructible = true;
    }

    public boolean checkForKey(Entity entity) {
        
        boolean isCorrect = false;

        if(entity.weapon1 instanceof Axe){
            return true;
        }

        return isCorrect;
    }

    public Color getParticleColor(){
        Color c = Color.GRAY;
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