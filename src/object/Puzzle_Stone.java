package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Puzzle_Stone extends Entity{

    GamePanel gp;

    public Puzzle_Stone(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        anim = new BufferedImage[4];
        anim[0] = setup("/res/obj/boulder.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/boulder1.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/obj/boulder2.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/obj/boulder3.png", gp.tileSize, gp.tileSize);


        pickable = false;
        collision = true;
        type = type_movable;
    }
}