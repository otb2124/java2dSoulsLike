package object;

import entity.Entity;
import main.GamePanel;

public class Shield_Wood extends Entity{

    GamePanel gp;

    public Shield_Wood(GamePanel _gp) {
        super(_gp);
        gp = _gp;
        
        type = type_weapon;
        name = "Shield Wood";
        anim[0] = setup("/res/obj/armor.png", gp.tileSize, gp.tileSize);

        protection = 5;

        descript = "Old Decaying Shield\nThis kinda sucks";
    }
    
}
