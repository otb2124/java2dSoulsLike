package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Tent extends Entity{

    GamePanel gp;

    public Tent(GamePanel _gp) {
        super(_gp);
        anim = new BufferedImage[8];

        gp = _gp;
        pickable = false;
        collision = true;
        
        getImage();
    }

    public void getImage(){
        anim[0] = setup("/res/obj/tent.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/tent.png", gp.tileSize, gp.tileSize);
    }
    
}

