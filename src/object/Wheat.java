package object;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import entity.Entity;
import main.GamePanel;

public class Wheat extends Entity{

    GamePanel gp;
    
    public Wheat(GamePanel _gp){
        super(_gp);
        gp = _gp;

        name = "Wheat";

        drawLim = 2;
        solidArea = new Rectangle(8, 16+48, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        type = type_grass;
        overDraw = true;

        anim = new BufferedImage[3];
        anim[0] = setup("/res/obj/wheat.png", gp.tileSize, gp.tileSize*2);
        anim[1] = setup("/res/obj/wheat1.png", gp.tileSize, gp.tileSize*2);
        anim[2] = setup("/res/obj/wheat2.png", gp.tileSize, gp.tileSize*2);
        collision = false;
        pickable = false;

    }
}
