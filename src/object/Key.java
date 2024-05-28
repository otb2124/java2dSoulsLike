package object;

import entity.Entity;
import main.GamePanel;

public class Key extends Entity{

    GamePanel gp;

    public Key(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        name = "Key";
        anim[0] = setup("/res/obj/key.png", gp.tileSize, gp.tileSize);
        collision = false;

        descript = "Old Rusty Key";

        stackable = true;

    }
}
