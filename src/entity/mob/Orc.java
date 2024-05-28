package entity.mob;

import java.awt.image.BufferedImage;
import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.Axe;
import object.Heart;
import object.Scythe;
import java.awt.Color;

public class Orc extends Entity{
    
    GamePanel gp;

    public Orc(GamePanel _gp){
        super(_gp);

        gp = _gp;

        type = 2;
        name = "Orc";
        maxhp = 200;
        hp = maxhp;
        attack = 15;
        def = 1;
        exp = 10;

        defaultSpeed = 2;
        speed = defaultSpeed;

        anim = new BufferedImage[16];

        weapon1 = new Scythe(gp);
        weapon2 = new Scythe(gp);

        solidArea.x = 10;
        solidArea.y = 18;
        solidArea.width = 25;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;

        knockBackPower = 50;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        anim[0] = setup("/res/mob/orc/orc_up_l.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/mob/orc/orc_up_r.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/mob/orc/orc_down_l.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/mob/orc/orc_down_r.png", gp.tileSize, gp.tileSize);
        anim[4] = setup("/res/mob/orc/orc_left.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/mob/orc/orc_left2.png", gp.tileSize, gp.tileSize);
        anim[6] = setup("/res/mob/orc/orc_right.png", gp.tileSize, gp.tileSize);
        anim[7] = setup("/res/mob/orc/orc_right2.png", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage(){

        if(weapon1 instanceof Scythe || weapon2 instanceof Scythe){
            anim[8] = setup("/res/mob/orc/orc_att_up.png", gp.tileSize, gp.tileSize*2);
            anim[9] = setup("/res/mob/orc/orc_att_up1.png", gp.tileSize, gp.tileSize*2);

            anim[10] = setup("/res/mob/orc/orc_att_down.png", gp.tileSize, gp.tileSize*2);
            anim[11] = setup("/res/mob/orc/orc_att_down1.png", gp.tileSize, gp.tileSize*2);

            anim[12] = setup("/res/mob/orc/orc_att_left.png", gp.tileSize*2, gp.tileSize);
            anim[13] = setup("/res/mob/orc/orc_att_left1.png", gp.tileSize*2, gp.tileSize);

            anim[14] = setup("/res/mob/orc/orc_att_right.png", gp.tileSize*2, gp.tileSize);
            anim[15] = setup("/res/mob/orc/orc_att_right1.png", gp.tileSize*2, gp.tileSize);
        }
        if(weapon1 instanceof Axe || weapon2 instanceof Axe){
            anim[8] = setup("/res/mob/orc/orc_att_up.png", gp.tileSize, gp.tileSize*2);
            anim[9] = setup("/res/mob/orc/orc_att_up_1.png", gp.tileSize, gp.tileSize*2);

            anim[10] = setup("/res/mob/orc/orc_att_down.png", gp.tileSize, gp.tileSize*2);
            anim[11] = setup("/res/mob/orc/orc_att_down1.png", gp.tileSize, gp.tileSize*2);

            anim[12] = setup("/res/mob/orc/orc_att_left.png", gp.tileSize*2, gp.tileSize);
            anim[13] = setup("/res/mob/orc/orc_att_left1.png", gp.tileSize*2, gp.tileSize);

            anim[14] = setup("/res/mob/orc/orc_att_right.png", gp.tileSize*2, gp.tileSize);
            anim[15] = setup("/res/mob/orc/orc_att_right1.png", gp.tileSize*2, gp.tileSize);
        }

        
}

    public void setAction(){
       
        if(aggroOnPlayer(5, 15)){
            searchPath(setTargetXAsGoal(gp.player), setTargetYAsGoal(gp.player));
        }
        else{
            
        }
        
    }
    public void update(){
        super.update();

       
        
    }
    public void dmgReact(){
        
    }
    


    public void checkDrop(){
        int i = new Random().nextInt(100)+1;


        
        if(i < 50){
            
        }
        if(i >= 50 && i < 100){
            dropItem(new Heart(gp));
        }
    }



    public Color getParticleColor(){
        Color c = Color.orange;
        return c;
    }
    public int getPartSize(){
        int size = 5;
        return size;
    }
    public int getParticleSpeed(){
        int partspeed = 5;
        return partspeed;
    }
    public int getPartMaxHp(){
        int maxLife = 10;
        return maxLife;
    }
}
