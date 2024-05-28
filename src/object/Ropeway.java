package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Ropeway extends Entity{

    GamePanel gp;

    public Ropeway(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        drawLim = 1;
        anim = new BufferedImage[5];
        anim[0] = setup("/res/obj/rope_stick.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/rope0.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/obj/rope1.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/obj/rope2.png", gp.tileSize, gp.tileSize);
        anim[4] = setup("/res/obj/rope3.png", gp.tileSize, gp.tileSize);

        type = type_ropeway;
        pickable = false;
        collision = true;
        overDraw = false;
    }
}