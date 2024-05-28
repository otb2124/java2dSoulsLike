package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Ladder extends Entity{

    GamePanel gp;

    public Ladder(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        anim = new BufferedImage[3];
        anim[0] = setup("/res/obj/ladder0.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/ladder1.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/obj/ladder2.png", gp.tileSize, gp.tileSize);

        type = type_ladder;
        pickable = false;
        collision = true;
    }
}