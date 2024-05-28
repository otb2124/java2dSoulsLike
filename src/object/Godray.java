package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Godray extends Entity{

    GamePanel gp;

    public Godray(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        drawLim = 8;
        anim = new BufferedImage[2];
        anim[0] = setup("/res/obj/godray.png", gp.tileSize*3, gp.tileSize*8);

        pickable = false;
        collision = false;
        overDraw = true;

        solidArea.y += gp.tileSize*5;
        solidAreaDefaultY = solidArea.y;
    }
}