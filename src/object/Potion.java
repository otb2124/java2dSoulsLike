package object;

import entity.Entity;
import main.GamePanel;

public class Potion extends Entity{

    GamePanel gp;

    
    public Potion(GamePanel _gp){
        super(_gp);
        gp = _gp;

        value = 10;
        type = type_consumable;
        name = "Potion";
        anim[0] = setup("/res/obj/heart.png", gp.tileSize, gp.tileSize);

        descript = "Healing shit";
    }

    public void use(Entity entity){
        entity.hp+=value;
        gp.playSFX(1);
    }
}

