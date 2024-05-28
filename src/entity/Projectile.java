package entity;
import main.GamePanel;
import java.awt.Rectangle;

public class Projectile extends Entity{

    Entity user;

    public Projectile(GamePanel _gp) {
        super(_gp);

        solidArea = new Rectangle(10, 0, 16, 16);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.hp = this.maxhp;
    }
    
    public void update(){

        if(user == gp.player){
            int mobID = gp.cc.checkEntity(this, gp.mob);
            if(mobID != 999){
                gp.player.damageMob(mobID, this, attack, knockBackPower, knockBackPower);
                generatPart("gravity", user.projectile, gp.mob[gp.currentMap][mobID]);
                alive = false;
            }
        }
        if(user != gp.player){
            boolean cPlayer = gp.cc.checkPlayer(this);
            if(gp.player.invincible == false && cPlayer == true){
                damagePlayer(attack);
                alive = false;
            }
        }

        switch (direction){
            case "up": worldY -= speed; solidArea.x = 15; solidArea.y = 0;break;
            case "down": worldY += speed; solidArea.x = 15; solidArea.y = 20;break;
            case "left": worldX -= speed; solidArea.x = 10; solidArea.y = 15;break;
            case "right": worldX += speed; solidArea.x = 20; solidArea.y = 15;break;
        }

        hp--;
        if(hp <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 0){
                spriteNum = 1;
            }
            else if(spriteNum == 1){
                spriteNum = 0;
            }
        }
    }


    public boolean haveResource(Entity user){

        boolean haveRes = false;
        if(user.mana >= projectileCost) {
            haveRes = true;
        }

        return haveRes;
    }
    public void substrRes(Entity user){
        user.mana =- projectileCost;
    }
}
