package object;

import entity.Entity;
import main.GamePanel;

public class Axe extends Entity{

    GamePanel gp;

    public Axe(GamePanel _gp) {
        super(_gp);
        gp = _gp;
        
        type = type_weapon;
        name = "Axe";
        anim[0] = setup("/res/obj/armor.png", gp.tileSize, gp.tileSize);

        dmg = 10;
        knockBackPower = 3;
        attackArea.width = 30;
        attackArea.height = 30;

        descript = "Old Rusty Axe\nThis kinda sucks but still in use";
    }
    
}
