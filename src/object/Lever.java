package object;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

public class Lever extends Entity{

    GamePanel gp;

    public Lever(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        name = "Lever";
        collision = true;
        pickable = false;
        isPressed = false;
        type = type_lever;
        anim[0] = setup("/res/obj/lever.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/lever1.png", gp.tileSize, gp.tileSize);
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

