package object;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

public class Plate extends Entity{

    GamePanel gp;

    public Plate(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        name = "Plate";
        collision = false;
        pickable = false;
        isPressed = false;
        type = type_plate;
        anim[0] = setup("/res/obj/plate0.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/plate.png", gp.tileSize, gp.tileSize);
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
