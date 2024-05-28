package object;

import entity.Entity;
import main.GamePanel;

public class Torch extends Entity{

    GamePanel gp;

    public Torch(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        name = "Torch";
        anim[0] = setup("/res/obj/lantern.png", gp.tileSize, gp.tileSize);
        collision = false;

        descript = "Old toech";
        type = type_weapon;
        emissionRadius = 250;

        dmg = 2;
        attackArea.width = 10;
        attackArea.height = 10;
        knockBackPower = 1;
    }
}
