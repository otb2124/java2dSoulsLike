package object;

import entity.Entity;
import main.GamePanel;

public class Lantern extends Entity{

    GamePanel gp;

    public Lantern(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        name = "Lantern";
        anim[0] = setup("/res/obj/lantern.png", gp.tileSize, gp.tileSize);
        collision = false;

        descript = "Old Lantern";
        type = type_weapon;
        emissionRadius = 100;

        dmg = 2;
        attackArea.width = 10;
        attackArea.height = 10;
        knockBackPower = 1;
    }
}
