package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Tree extends Entity{

    GamePanel gp;

    public Tree(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        drawLim = 6;
        anim = new BufferedImage[2];
        anim[0] = setup("/res/obj/tree.png", gp.tileSize*6, gp.tileSize*6);

        pickable = false;
        collision = true;
        overDraw = false;

        solidArea.y += gp.tileSize*5;
        solidAreaDefaultY = solidArea.y;
    }
}