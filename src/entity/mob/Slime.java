package entity.mob;

import java.awt.image.BufferedImage;
import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.Heart;
import object.Rock;

public class Slime extends Entity{
    
    GamePanel gp;

    public Slime(GamePanel _gp){
        super(_gp);

        gp = _gp;

        type = 2;
        name = "Slime";
        maxhp = 50;
        hp = maxhp;
        attack = 15;
        def = 1;
        exp = 4;
        projectile = new Rock(gp);

        defaultSpeed = 1;
        speed = defaultSpeed;

        anim = new BufferedImage[16];

        solidArea.x = 10;
        solidArea.y = 18;
        solidArea.width = 25;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;

        knockBackPower = 10;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        anim[0] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
        anim[4] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
        anim[6] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[7] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage(){
        anim[8] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[9] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
        anim[10] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[11] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
        anim[12] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[13] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
        anim[14] = setup("/res/mob/slime/up1.png", gp.tileSize, gp.tileSize);
        anim[15] = setup("/res/mob/slime/up2.png", gp.tileSize, gp.tileSize);
    }

    public void setAction(){

        

        if(aggroOnPlayer(5, 15)){
            searchPath(setTargetXAsGoal(gp.player), setTargetYAsGoal(gp.player));
        }
        else{
            randomWalk();
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
}
