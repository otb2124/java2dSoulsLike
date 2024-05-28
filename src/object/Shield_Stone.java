package object;

import entity.Entity;
import main.GamePanel;

public class Shield_Stone extends Entity{

    GamePanel gp;

    public Shield_Stone(GamePanel _gp) {
        super(_gp);
        gp = _gp;
        
        type = type_weapon;
        name = "Stone Shield";
        anim[0] = setup("/res/obj/armor.png", gp.tileSize, gp.tileSize);

        protection = 6;

        descript = "Old Rusty Stone Shield\nThis kinda sucks but still in use";
    }
    
}
