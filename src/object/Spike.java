package object;

import entity.Entity;
import main.GamePanel;

public class Spike extends Entity{

    GamePanel gp;
    
    public Spike(GamePanel _gp){
        super(_gp);
        gp = _gp;

        name = "Spike";
        anim[0] = setup("/res/obj/spike.png", gp.tileSize, gp.tileSize);
        collision = false;

        pickable = false;

    }
}
