package object;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity{

    GamePanel gp;
    
    public Heart(GamePanel _gp){
        super(_gp);
        gp = _gp;

        name = "Heart";
        type = type_autoConsumable;
        value = 25;
        anim[0] = setup("/res/obj/heart.png", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){
        entity.hp+=value;
        gp.playSFX(1);
    }
}