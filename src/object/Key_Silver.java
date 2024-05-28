package object;

import entity.Entity;
import main.GamePanel;

public class Key_Silver extends Entity{

    GamePanel gp;

    public Key_Silver(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        name = "Silver Key";
        anim[0] = setup("/res/obj/key.png", gp.tileSize, gp.tileSize);
        collision = false;

        descript = "Old Silver Key";

    }
}
