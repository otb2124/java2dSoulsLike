package entity;

import main.GamePanel;
import object.Axe;
import object.Key;
import object.Potion;
import object.Scythe;

import java.awt.image.BufferedImage;

public class Merchant extends Entity{
    
    public Merchant(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 0;

        anim = new BufferedImage[8];

        getImage();
        setDialogue();
        setItem();
    }

    public void getImage(){
        anim[0] = setup("/res/npc/bard/player_up_r.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/npc/bard/player_up_l.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/npc/bard/player_down_l.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/npc/bard/player_down_r.png", gp.tileSize, gp.tileSize);
        anim[4] = setup("/res/npc/bard/player_left.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/npc/bard/player_left2.png", gp.tileSize, gp.tileSize);
        anim[6] = setup("/res/npc/bard/player_right.png", gp.tileSize, gp.tileSize);
        anim[7] = setup("/res/npc/bard/player_right2.png", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        
    }

    public void setDialogue(){
        dialogues[0] = "Good day.";
        dialogues[1] = "AAAAAAAAAAAA\nAAAAAAAAA\nAAAAAAAAAA";
        dialogues[2] = "Good day.";
    }

    public void speak(){
        super.speak();
        
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }

    public void setItem(){
        inv.add(new Potion(gp));
        inv.add(new Scythe(gp));
        inv.add(new Axe(gp));
        inv.add(new Key(gp));
        inv.add(new Potion(gp));
    }
}
