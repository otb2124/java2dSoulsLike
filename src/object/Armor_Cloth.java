package object;

import entity.Entity;
import main.GamePanel;

public class Armor_Cloth extends Entity{

    GamePanel gp;

    public Armor_Cloth(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        type = type_armor;
        name = "Armor Cloth";        
        anim[0] = setup("/res/obj/armor.png", gp.tileSize, gp.tileSize);

        protection = 5;

        descript = "Old Ripped Cloth\nThis kinda sucks";
       
    }
}