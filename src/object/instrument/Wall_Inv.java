package object.instrument;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Wall_Inv extends Entity{

    GamePanel gp;

    public Wall_Inv(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        anim = new BufferedImage[1];
        anim[0] = setup("/res/obj/null.png", gp.tileSize*6, gp.tileSize*6);

        pickable = false;
        collision = true;
    }
}