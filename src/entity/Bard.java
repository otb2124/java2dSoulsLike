package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Bard extends Entity{
    
    private boolean Asleep = true;
    private int sleepPartCount = 0, playCount = 0;

    public Bard(GamePanel gp){
        super(gp);

        direction = "down";

        type = type_npc;
        drawLim = 2;

        defaultSpeed = 3;
        speed = defaultSpeed;

        solidArea.x = 10;
        solidArea.y = 40;
        solidArea.width = 45;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        anim = new BufferedImage[16];

        getImage();
        setDialogue();
    }

    public void getImage(){

        anim[0] = setup("/res/npc/bard/player_up_r.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/npc/bard/player_up_l.png", gp.tileSize, gp.tileSize);
        anim[2] = setup("/res/npc/bard/down1.png", 96,  96);
        anim[3] = setup("/res/npc/bard/down.png",  96,  96);
        anim[4] = setup("/res/npc/bard/player_left.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/npc/bard/player_left2.png", gp.tileSize, gp.tileSize);
        anim[6] = setup("/res/npc/bard/player_right.png", gp.tileSize, gp.tileSize);
        anim[7] = setup("/res/npc/bard/player_right2.png", gp.tileSize, gp.tileSize);

        anim[8] = setup("/res/npc/bard/play0.png", 96, 96);
        anim[9] = setup("/res/npc/bard/play1.png", 96, 96);
        anim[10] = setup("/res/npc/bard/play2.png", 96, 96);
    }

    public void setAction(){
       
        if(Asleep == true){
            spriteNum = 1;

            Random rn = new Random();
            int rando = rn.nextInt(5);

            sleepPartCount+=rando;

            if(sleepPartCount == 60){
                generatPart("gas", this, this, 10+(rando*2), -5-(rando*2), true);
            }
            if(sleepPartCount == 120){
                generatPart("gas", this, this, 20+(rando*2), -10-(rando*2), true);
            }
            if(sleepPartCount == 230){
                generatPart("gas", this, this, 15+(rando*2), -(rando*3), true);
            }
            if(sleepPartCount >= 240){
                sleepPartCount = 0;
            }
        }
        else {
            spriteNum = 0;
            spriteCounter++;
        }

        if(spriteCounter >= 180 && spriteCounter < 201){
            play();
        }

        if(spriteCounter == 360){
            Asleep = true;
            spriteCounter = 0;
        }






    }

    public void setDialogue(){
        dialogues[0] = "Hello sucker";
        dialogues[1] = "AAAAAAAAAAAA\nAAAAAAAAA\nAAAAAAAAAA";
        dialogues[2] = "Hello dicksucker";
    }

    public void speak(){
        super.speak();

        Asleep = false;
    }


    public BufferedImage getPartImg(){
        return setup("/res/particles/sleep.png", gp.tileSize/2, gp.tileSize/2);
    }
    public int getPartSize(){
        int _size = 10;
        return _size;
    }
    public int getParticleSpeed(){
        int partspeed = 1;
        return partspeed;
    }
    public int getPartMaxHp(){
        int maxLife = 20;
        return maxLife;
    }





    public void play(){
        playCount++;

        if(playCount >= 1 && playCount < 10){
            spriteNum = 8;
        }
        if(playCount >= 10 && playCount < 30){
            spriteNum = 9;
        }
        if(playCount >= 21 && playCount < 30){
            gp.playSFX(3);
        }
        if(playCount >= 30){
            spriteNum = 10;
            playCount = 0;
        }
    }
}
