package object.effects;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Explosion extends Entity{

    GamePanel gp;

    public Explosion(GamePanel _gp) {
        super(_gp);
        gp = _gp;

        anim = new BufferedImage[6];
        anim[0] = setup("/res/effects/explosion0.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/effects/explosion1.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/effects/explosion2.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/effects/explosion3.png", gp.tileSize, gp.tileSize);
        anim[4] = setup("/res/effects/explosion4.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/effects/explosion5.png", gp.tileSize, gp.tileSize);

        type = type_effect;
        maxhp = 30;
        hp = maxhp;

        pickable = false;
        collision = false;
        overDraw = true;
    }
}