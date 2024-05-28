package object;

import entity.Entity;
import main.GamePanel;

public class Scythe extends Entity{

    GamePanel gp;

    public Scythe(GamePanel _gp) {
        super(_gp);
        gp = _gp;
        
        type = type_weapon;
        name = "Scythe";
        anim[0] = setup("/res/obj/armor.png", gp.tileSize, gp.tileSize);

        dmg = 5;
        attackArea.width = 48;
        attackArea.height = 48;
        knockBackPower = 10;

        descript = "Old Rusty Scythe\nThis kinda sucks but still in use";
    }
    
}
