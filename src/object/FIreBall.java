package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import java.awt.Color;

public class FIreBall extends Projectile{

    GamePanel gp;

    public FIreBall(GamePanel _gp) {
        super(_gp);
        anim = new BufferedImage[8];

        gp = _gp;
        speed = 5;
        maxhp = 70;
        hp = maxhp;
        attack = 10;
        projectileCost = 10;
        knockBackPower = 0;

        alive = false;
        
        getImage();
    }

    public void getImage(){
        anim[0] = setup("/res/projectile/fireball/fireball_up1.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/projectile/fireball/fireball_up2.png", gp.tileSize, gp.tileSize);

        anim[2] = setup("/res/projectile/fireball/fireball_down1.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/projectile/fireball/fireball_down2.png", gp.tileSize, gp.tileSize);

        anim[4] = setup("/res/projectile/fireball/fireball_left1.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/projectile/fireball/fireball_left2.png", gp.tileSize, gp.tileSize);

        anim[6] = setup("/res/projectile/fireball/fireball_right1.png", gp.tileSize, gp.tileSize);
        anim[7] = setup("/res/projectile/fireball/fireball_right2.png", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user){

        boolean haveRes = false;
        if(user.mana >= projectileCost) {
            haveRes = true;
        }

        return haveRes;
    }

    public void substrRes(Entity user){
        user.mana -= projectileCost;
    }


    public Color getParticleColor(){
        Color c = Color.red;
        return c;
    }
    public int getPartSize(){
        int _size = 6;
        return _size;
    }
    public int getParticleSpeed(){
        int partspeed = 2;
        return partspeed;
    }
    public int getPartMaxHp(){
        int maxLife = 15;
        return maxLife;
    }
    
}
