package object;

import entity.Entity;
import main.GamePanel;

public class TPStone extends Entity{

    GamePanel gp;

    public TPStone(GamePanel _gp){
        super(_gp);

        gp = _gp;
        name = "TPStone";
        anim[0] = setup("/res/obj/teleport_stone.png", gp.tileSize, gp.tileSize);
        collision = true;

        pickable = false;

    }
}