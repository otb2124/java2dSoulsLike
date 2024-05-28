package object;

import java.awt.Color;
import java.awt.image.BufferedImage;
import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Rock extends Projectile{

    GamePanel gp;

    public Rock(GamePanel _gp) {
        super(_gp);
        anim = new BufferedImage[8];

        gp = _gp;
        speed = 10;
        maxhp = 70;
        hp = maxhp;
        attack = 15;
        projectileCost = 1;

        alive = false;
        
        getImage();
    }

    public void getImage(){
        anim[0] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);

        anim[2] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);
        anim[3] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);

        anim[4] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);
        anim[5] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);

        anim[6] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);
        anim[7] = setup("/res/projectile/rock/rock_down1.png", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user){

        boolean haveRes = false;
        if(user.ammo >= projectileCost) {
            haveRes = true;
        }

        return haveRes;
    }

    public void substrRes(Entity user){
        user.ammo -= projectileCost;
    }

    public Color getParticleColor(){
        Color c = Color.gray;
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

