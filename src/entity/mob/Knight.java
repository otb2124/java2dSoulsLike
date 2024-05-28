package entity.mob;

import java.awt.image.BufferedImage;
import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.Heart;
import object.Scythe;
import java.awt.Color;

public class Knight extends Entity{
    
    GamePanel gp;

    public Knight(GamePanel _gp){
        super(_gp);

        gp = _gp;

        type = 2;
        name = "Knight";
        maxhp = 300;
        hp = maxhp;
        attack = 15;
        def = 1;
        exp = 10;
        direction = "down";

        defaultSpeed = 2;
        speed = defaultSpeed;

        anim = new BufferedImage[16];

        weapon1 = new Scythe(gp);
        weapon2 = new Scythe(gp);

        solidArea.x = 10+40;
        solidArea.y = 18+80;
        solidArea.width = 25;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;

        knockBackPower = 50;

        getImage();

    }

    public void getImage(){
        anim[0] = setup("/res/mob/knight/up.png", 64*2, 64*2);
        anim[1] = setup("/res/mob/knight/up.png", 64*2, 64*2);
        anim[2] = setup("/res/mob/knight/down.png", 64*2, 64*2);
        anim[3] = setup("/res/mob/knight/down.png", 64*2, 64*2);
        anim[4] = setup("/res/mob/knight/left.png", 64*2, 64*2);
        anim[5] = setup("/res/mob/knight/left.png", 64*2, 64*2);
        anim[6] = setup("/res/mob/knight/right.png", 64*2, 64*2);
        anim[7] = setup("/res/mob/knight/right.png", 64*2, 64*2);
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

