package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Armor_Cloth;
import object.Axe;
import object.FIreBall;
import object.Key;
import object.Key_Silver;
import object.Lantern;
import object.Potion;
import object.Scythe;
import object.Shield_Wood;
import object.Tent;
import object.Torch;
import object.instrument.PlayerReflection;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.awt.Color;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    public boolean lightUpdated = false;
    public boolean sleeps = false;
    public boolean onLadder = false, fallDeathAnim = false;
    
    public int wCount = 0, dCount = 0, pCount = 0, particleCounter = 0, ladderCounter = 0;
    public int screenX, screenY;
    public final int screenXDef, screenYDef;


    public Player(GamePanel _gp, KeyHandler _keyH){

        super(_gp);
        
        gp = _gp;
        keyH = _keyH;

        screenXDef = gp.screenWidth/2 - gp.tileSize/2;
        screenYDef = gp.screenHeigth/2 - gp.tileSize/2;
        screenX = screenXDef;
        screenY = screenYDef;

        anim = new BufferedImage[16];

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttImage();
        setItem();
    }

    public void setDefaultValues(){
        gp.tm.tile[0].isOverDrawn = true;

        if(fallDeathAnim == true){
            gp.em.rain.size = 10;
            fallDeathAnim = false;
        }
        
        drawing = true;
        gp.currentMap = 0;
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        direction = "down";
        defaultSpeed = 3;
        speed = defaultSpeed;

        //stats
        maxhp = 100;
        hp = maxhp;
        maxMana = 100;
        mana = maxMana;
        maxEndurance = 100;
        endurance = maxEndurance;
        enduranceRegen = 60;

        lvl = 0;
        strength = 1;
        dexerity = 1;
        will = 1;
        intellect = 1;
        exp = 0;
        toNextLvl = 10;

        dodgeLength = 20;
        parryLength = 60;

        weapon1 = new Axe(gp);
        weapon2 = new Shield_Wood(gp);
        armor = new Armor_Cloth(gp);
        projectile = new FIreBall(gp);

        attack = getPlayerAttack();
        def = getPlayerDef();
    }

    public void setItem(){
        inv.add(weapon1);
        inv.add(weapon2);
        inv.add(armor);
        inv.add(new Key(gp));
        inv.get(3).ammount = 5;
        inv.add(new Key_Silver(gp));
        inv.add(new Potion(gp));
        inv.add(new Scythe(gp));
        inv.add(new Lantern(gp));
        inv.add(new Torch(gp));
    }

    public int getPlayerAttack(){
        attackArea.width = Math.max(weapon1.attackArea.width, weapon2.attackArea.width);
        attackArea.height = Math.max(weapon1.attackArea.height, weapon2.attackArea.height);
        return strength*weapon1.dmg + strength*weapon2.dmg;
    }
    public int getPlayerDef(){
        return armor.protection + weapon1.protection + weapon2.protection;
    }









    public void getPlayerImage(){
            anim[0] = setup("/res/player/player_up_r.png", gp.tileSize, gp.tileSize);
            anim[1] = setup("/res/player/player_up_l.png", gp.tileSize, gp.tileSize);

            anim[2] = setup("/res/player/player_down_l.png", gp.tileSize, gp.tileSize);
            anim[3] = setup("/res/player/player_down_r.png", gp.tileSize, gp.tileSize);

            anim[4] = setup("/res/player/player_left.png", gp.tileSize, gp.tileSize);
            anim[5] = setup("/res/player/player_left2.png", gp.tileSize, gp.tileSize);

            anim[6] = setup("/res/player/player_right.png", gp.tileSize, gp.tileSize);
            anim[7] = setup("/res/player/player_right2.png", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttImage(){
            if(weapon1 instanceof Scythe || weapon2 instanceof Scythe){
                anim[8] = setup("/res/player/player_att_up.png", gp.tileSize, gp.tileSize*2);
                anim[9] = setup("/res/player/player_att_up1.png", gp.tileSize, gp.tileSize*2);

                anim[10] = setup("/res/player/player_att_down.png", gp.tileSize, gp.tileSize*2);
                anim[11] = setup("/res/player/player_att_down1.png", gp.tileSize, gp.tileSize*2);

                anim[12] = setup("/res/player/player_att_left.png", gp.tileSize*2, gp.tileSize);
                anim[13] = setup("/res/player/player_att_left1.png", gp.tileSize*2, gp.tileSize);

                anim[14] = setup("/res/player/player_att_right.png", gp.tileSize*2, gp.tileSize);
                anim[15] = setup("/res/player/player_att_right1.png", gp.tileSize*2, gp.tileSize);
            }
            if(weapon1 instanceof Axe || weapon2 instanceof Axe){
                anim[8] = setup("/res/player/player_att_up.png", gp.tileSize, gp.tileSize*2);
                anim[9] = setup("/res/player/player_att_up1.png", gp.tileSize, gp.tileSize*2);

                anim[10] = setup("/res/player/player_att_down.png", gp.tileSize, gp.tileSize*2);
                anim[11] = setup("/res/player/player_att_down1.png", gp.tileSize, gp.tileSize*2);

                anim[12] = setup("/res/player/player_att_left.png", gp.tileSize*2, gp.tileSize);
                anim[13] = setup("/res/player/player_att_left1.png", gp.tileSize*2, gp.tileSize);

                anim[14] = setup("/res/player/player_att_right.png", gp.tileSize*2, gp.tileSize);
                anim[15] = setup("/res/player/player_att_right1.png", gp.tileSize*2, gp.tileSize);
            }
    }










    public void update(){

        //attacking
        if(attacking == true){
            attacking();
        }
        //WHEN WASD
    	else if(keyH.upPressed == true || keyH.leftPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.dialoguePressed == true || keyH.attackPressed == true || keyH.dodgePressed == true || keyH.parryPressed == true) {

            if(onLadder == true){
                if(keyH.downPressed == true){direction = "down";}
                else if(keyH.upPressed == true){direction = "up";}
            }
            else {
                //WASD
                if(keyH.upPressed == true){direction = "up";}
                else if(keyH.downPressed == true){direction = "down";}
                else if(keyH.leftPressed == true){direction = "left";}
                else if(keyH.rightPressed == true){direction = "right";}
            }
            

            
            
            //ATT
            if(keyH.attackPressed == true){attacking = true;}



            //collision
            collisionOn = false;

            //tile
            gp.cc.checkTile(this);

            //obj
            int objIndex = gp.cc.checkObject(this, true);
            pickUpObject(objIndex);

            //npc
            int npcIndex = gp.cc.checkEntity(this, gp.npc);
            interractNPC(npcIndex);

            //mob
            int mobID = gp.cc.checkEntity(this, gp.mob);

            //event
            gp.eh.checkEvent();

            //liquids
            if(gp.tm.tile[gp.tm.mapTileNum[gp.currentMap][((worldX+20)/gp.tileSize)][(worldY/gp.tileSize)+1]].isLiquid == true){
                speed = 1;
                particleCounter++;

                if(particleCounter >= 20){
                    generatPart("gravity", this, Color.blue, 7, 1, 15, 0, 10);
                    particleCounter = 0;
                }
            }

            //hills
            else if(gp.tm.tile[gp.tm.mapTileNum[gp.currentMap][((worldX+20)/gp.tileSize)][(worldY/gp.tileSize)+1]].isHill == true){
                speed = 5;
            }
            else{
                speed = defaultSpeed;
            }


            if(collisionOn == false && keyH.dialoguePressed == false && sleeps == false && attacking == false && drawing == true){
                if(keyH.parryPressed == true && endurance > 0 && enduranceCount == 0){
                    parry();
                }
                else if(keyH.dodgePressed == true && endurance > 0 && enduranceCount == 0){
                    dodge();
                }
                else{

                    if(onLadder == false && onRopeWay == false){
                        switch(direction){
                            case "up":
                                worldY -= speed;
                                break;
                            case "down":
                                worldY += speed;
                                break;
                            case "right":
                                worldX += speed;
                                break;
                            case "left":
                                worldX -= speed;
                                break;
                        }
                    }
                    

                    if(isFalling == true){
                        particleCounter++;

                        if(particleCounter >= 7){
                            generatPart("dust", this, Color.orange, 1, 1, 20, 0, 20);
                            particleCounter = 0;
                        }
                        
                    }
                }

            
            }
            else {
                gp.keyH.dialoguePressed = false;
            }


            if(isParrying == true){

            }
            else {
                spriteCounter++;
                if(spriteCounter > 5) {
                    if(spriteNum == 0) {
                        spriteNum = 1;
                    }
                    else if(spriteNum == 1) {
                        spriteNum = 0;
                    }
                    spriteCounter = 0;
                }
            }



            if(onLadder == true){
                switch(direction){
                    case "up": worldY -= 1; break;
                    case "down": worldY += 1; break;
                }
            }
            if(onRopeWay == true){
                switch(direction){
                    case "up": worldY -= 1; break;
                    case "down": worldY += 1; break;
                    case "left": worldX -= 1; break;
                    case "right": worldX += 1; break;
                }
            }

            if(ropeWayBalance > 10 || ropeWayBalance < -10){
                onRopeWay = false;
                ropeWayBalance = 0;
                fallDeathAnim = true;
            }
        }







        //CONSTANT
            if(invincible == true){
                invincibleCounter++;
                if(invincibleCounter > 60){
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if(hp > maxhp){
                hp = maxhp;
            }
            if(mana > maxMana){
                mana = maxMana;
            }
            if(hp <= 0){
                gp.gameState = gp.deathState;
            }


            if(endurance <= 0){
                keyH.dodgePressed = false;
                keyH.parryPressed = false;
                enduranceCount++;
            }
            if(enduranceCount >= enduranceRegen){
                endurance++;

                if(endurance == maxEndurance){
                    enduranceCount = 0;
                }
                
            }  
    		
    	
            if(gp.keyH.manaPressed == true && projCD == 30 && projectile.haveResource(this) == true){
                
                projectile.set(worldX, worldY, direction, true, this);
                projectile.substrRes(this);

                for (int i = 0; i < gp.projectile[1].length; i++) {
                    if(gp.projectile[gp.currentMap][i] == null){
                        gp.projectile[gp.currentMap][i] = projectile;
                        break;
                    }
                }
                gp.playSFX(1);
                projCD = 0;
                
            }

            if(projCD < 30) {
                projCD++;
            }
        
        

            
    }







    public void attacking(){
        
        attCounter++;

        if(attCounter <= 5){
            spriteNum = 0;
        }
        if(attCounter > 5 && attCounter <= 25){
            spriteNum = 1;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //normalizing charachter location when att
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

                int mobID = gp.cc.checkEntity(this, gp.mob);

                int projID = gp.cc.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projID);

                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width = solidAreaWidth;
                solidArea.height = solidAreaHeight;

                gp.player.damageMob(mobID, this, attack, weapon1.knockBackPower, weapon2.knockBackPower);
            
        }
            if(attCounter > 25){
                spriteNum = 0;
                attCounter = 0;
                attacking = false;
            }
            

            
    }




    public void pickUpObject(int i){
        if(i != 999){

            //autocons
            if(gp.obj[gp.currentMap][i].type == type_autoConsumable){
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }

            //toInv
            else if(gp.obj[gp.currentMap][i].collision == false && gp.obj[gp.currentMap][i].pickable == true){
                String text;
            
                if(canObtainItem(gp.obj[gp.currentMap][i]) == true){
                    text = gp.obj[gp.currentMap][i].name;
                }
                else {
                    text = "Inventory is full";
                }
                gp.ui.showMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }



            //grass
            else if(gp.obj[gp.currentMap][i].type == type_grass){

                if(gp.obj[gp.currentMap][i].spriteNum != 3){
                    gp.obj[gp.currentMap][i].spriteCounter++;

                    if(gp.obj[gp.currentMap][i].spriteCounter == 0){
                        gp.obj[gp.currentMap][i].spriteNum = 0;
                    }
                    if(gp.obj[gp.currentMap][i].spriteCounter > 0 && gp.obj[gp.currentMap][i].spriteCounter <= 10){
                        gp.obj[gp.currentMap][i].spriteNum = 1;
                    }
                    if(gp.obj[gp.currentMap][i].spriteCounter > 10 && gp.obj[gp.currentMap][i].spriteCounter <= 20){
                        gp.obj[gp.currentMap][i].spriteNum = 2;
                    }
                    if(gp.obj[gp.currentMap][i].spriteCounter > 20){
                        gp.obj[gp.currentMap][i].spriteCounter = 0;
                    }
                }
                else {
                    if(!direction.equals("down")){
                        gp.obj[gp.currentMap][i].overDraw = true;
                    }
                    else{
                        gp.obj[gp.currentMap][i].overDraw = false;
                    }
                }
            }


            
            //moving on ropes
            else if(gp.obj[gp.currentMap][i].type == type_ropeway && onRopeWay == true){
                if(gp.obj[gp.currentMap][i].spriteNum == 1){
                    switch(direction){
                        case "left": ropeWayBalance--; break;
                        case "right": ropeWayBalance++; break;
                    }
                }
                if(gp.obj[gp.currentMap][i].spriteNum == 2){
                    switch(direction){
                        case "up": ropeWayBalance--; break;
                        case "down": ropeWayBalance++; break;
                    }  
                }
                //downup leftright
                if(gp.obj[gp.currentMap][i].spriteNum == 3){
                    switch(direction){
                        case "left": ropeWayBalance--; break;
                        case "right": ropeWayBalance++; break;
                    }
                }

                //updown leftright
                if(gp.obj[gp.currentMap][i].spriteNum == 4){
                    switch(direction){
                        case "up": ropeWayBalance--; break;
                        case "down": ropeWayBalance++; break;
                    }  
                }    
            }
            
            






            else if(gp.obj[gp.currentMap][i].collision == true){

                //chest
                if(gp.obj[gp.currentMap][i].openable == true){
                        if(keyH.dialoguePressed == true && gp.obj[gp.currentMap][i].checkForKey(this) == true){
                            generatPart("gravity", gp.obj[gp.currentMap][i], gp.obj[gp.currentMap][i]);
                            gp.obj[gp.currentMap][i].anim[0] = gp.obj[gp.currentMap][i].anim[1];
                            gp.obj[gp.currentMap][i].openable = false;
                            keyH.dialoguePressed = false;
                        }
                }

                //destruct
                if(gp.obj[gp.currentMap][i].destructible == true){
                    if(attacking == true && gp.obj[gp.currentMap][i].checkForKey(this) == true){
                        generatPart("gravity", gp.obj[gp.currentMap][i], gp.obj[gp.currentMap][i]);
                        if(gp.obj[gp.currentMap][i].type == type_mirror){
                            gp.obj[gp.currentMap][i].actionLockCounter++;

                            if(gp.obj[gp.currentMap][i].actionLockCounter == 1){
                                gp.obj[gp.currentMap][i].spriteNum = 7;
                            }
                            if(gp.obj[gp.currentMap][i].actionLockCounter == 2){
                                gp.obj[gp.currentMap][i].spriteNum = 6;
                            }
                            if(gp.obj[gp.currentMap][i].actionLockCounter == 3){
                                gp.obj[gp.currentMap][i].spriteNum = 8;
                                gp.obj[gp.currentMap][i].collision = false;
                                gp.obj[gp.currentMap][i].destructible = false;
                            }
                        }
                        else {
                            gp.obj[gp.currentMap][i].spriteNum = 1;
                            gp.obj[gp.currentMap][i].collision = false;
                            gp.obj[gp.currentMap][i].destructible = false;
                        }
                        
                        
                        
                    }
                }

                //tent
                if(gp.obj[gp.currentMap][i] instanceof Tent){
                    if(gp.keyH.dialoguePressed == true && sleeps == false){
                        gp.em.light.dayCycleSpeed = 60;
                        gp.em.light.dayCycleFloat = 0.01f;
                        if(direction.equals("up")){
                            screenX = gp.obj[gp.currentMap][i].worldX - worldX + screenX;
                            screenY = gp.obj[gp.currentMap][i].worldY - worldY + screenY + gp.obj[gp.currentMap][i].solidArea.height*2;
                        }
                        if(direction.equals("down")){
                            screenX = gp.obj[gp.currentMap][i].worldX - worldX + screenX;
                            screenY = gp.obj[gp.currentMap][i].worldY - worldY + screenY - gp.obj[gp.currentMap][i].solidArea.height*2;
                        }
                        if(direction.equals("left")){
                            screenX = gp.obj[gp.currentMap][i].worldX - worldX + screenX + gp.obj[gp.currentMap][i].solidArea.width*2;
                            screenY = gp.obj[gp.currentMap][i].worldY - worldY + screenY;
                        }
                        if(direction.equals("right")){
                            screenX = gp.obj[gp.currentMap][i].worldX - worldX + screenX - gp.obj[gp.currentMap][i].solidArea.width*2;
                            screenY = gp.obj[gp.currentMap][i].worldY - worldY + screenY;
                        }
                        
                        sleeps = true;
                        keyH.dialoguePressed = false;
                    }
                    if(gp.keyH.dialoguePressed == true && sleeps == true){
                        gp.em.light.dayCycleSpeed = gp.em.light.dayCycleSpeedDefault;
                        gp.em.light.dayCycleFloat = gp.em.light.dayCycleFloatDefault;
                        screenX = screenXDef;
                        screenY = screenYDef;

                        sleeps = false;
                        keyH.dialoguePressed = false;
                        
                    }
                }



                //movable
                if(gp.obj[gp.currentMap][i].type == type_movable){

                    speed = 1;
                        switch(direction) {
                            case "up": gp.obj[gp.currentMap][i].worldY-=speed; break;
                            case "down": gp.obj[gp.currentMap][i].worldY+=speed; break;
                            case "left": gp.obj[gp.currentMap][i].worldX-=speed; break;
                            case "right": gp.obj[gp.currentMap][i].worldX+=speed; break;
                        }


                        gp.obj[gp.currentMap][i].spriteCounter++;

                        if(gp.obj[gp.currentMap][i].spriteCounter == 5){
                            gp.obj[gp.currentMap][i].spriteNum++;
                            if(gp.obj[gp.currentMap][i].spriteNum == 3){
                                gp.obj[gp.currentMap][i].spriteNum = 0;
                            }
                            gp.obj[gp.currentMap][i].spriteCounter = 0;
                        }
                        

                        speed = defaultSpeed;
                }



                //lever
                if(gp.obj[gp.currentMap][i].type == type_lever && keyH.dialoguePressed == true){

                    if(gp.obj[gp.currentMap][i].spriteNum == 0){
                        gp.obj[gp.currentMap][i].spriteNum = 1;

                        gp.as.setLadder(0);
                    }
                    else if(gp.obj[gp.currentMap][i].spriteNum == 1){
                        gp.obj[gp.currentMap][i].spriteNum = 0;

                        gp.as.deleteLadder(0);
                    }
                }


                //ladder
                if(gp.obj[gp.currentMap][i].type == type_ladder && onLadder == false && keyH.dialoguePressed == true && gp.obj[gp.currentMap][i].spriteNum == 0){
                    worldX = gp.obj[gp.currentMap][i].worldX;
                    onLadder = true; 
                    worldY-=gp.tileSize;                
                }
                if(gp.obj[gp.currentMap][i].type == type_ladder && onLadder == true && gp.obj[gp.currentMap][i].spriteNum == 2){
                    ladderCounter++;
                    if(ladderCounter >= 30){
                        onLadder = false; 
                        worldY-=gp.tileSize;
                        ladderCounter = 0;  
                    }                
                }
                if(gp.obj[gp.currentMap][i].type == type_ladder && onLadder == false && keyH.dialoguePressed == true && gp.obj[gp.currentMap][i].spriteNum == 2){
                    onLadder = true; 
                    worldX = gp.obj[gp.currentMap][i].worldX;
                    worldY+=gp.tileSize/2;                 
                }
                if(gp.obj[gp.currentMap][i].type == type_ladder && onLadder == true && gp.obj[gp.currentMap][i].spriteNum == 0){
                    ladderCounter++;
                    if(ladderCounter >= 60){
                        onLadder = false; 
                        worldY+=gp.tileSize;
                        ladderCounter = 0;  
                    }
                                  
                }

                //ropeway sticks
                if(gp.obj[gp.currentMap][i].type == type_ropeway && onRopeWay == false && keyH.dialoguePressed == true && ladderCounter == 0){
                    worldX = gp.obj[gp.currentMap][i].worldX;
                    worldY = gp.obj[gp.currentMap][i].worldY;

                    switch(gp.obj[gp.currentMap][i].direction){
                        case "up": worldY+=gp.tileSize; break;
                        case "down": worldY-=gp.tileSize; break;
                        case "left": worldX+=gp.tileSize; break;
                        case "right": worldX-=gp.tileSize; break;
                    } 
                    onRopeWay = true;
                }
                /*if(gp.obj[gp.currentMap][i].type == type_ropeway && onRopeWay == true && ladderCounter == 0){
                        switch(gp.obj[gp.currentMap][i].direction){
                            case "up": worldY-=gp.tileSize; break;
                            case "down": worldY+=gp.tileSize; break;
                            case "left": worldX-=gp.tileSize; break;
                            case "right": worldX+=gp.tileSize; break;
                        } 
                        onRopeWay = false;
                } */    
            }
        }
    }
   










    //action
    

    public void damageMob(int i, Entity attacker, int attack, int weapon1_knockback, int weapon2_knockback){
        if(i != 999){

            //hit
            if(gp.mob[gp.currentMap][i].invincible == false){


                if(Math.max(weapon1_knockback, weapon2_knockback) > 0){
                    knockBack(gp.mob[gp.currentMap][i], attacker, Math.max(weapon1_knockback, weapon2_knockback));
                }
                

                Damage = attack - gp.mob[gp.currentMap][i].def;
                if(Damage < 0){
                    Damage = 0;
                }
                gp.playSFX(1);
                gp.mob[gp.currentMap][i].hp -= Damage;
                gp.mob[gp.currentMap][i].invincible = true;
                gp.mob[gp.currentMap][i].dmgReact();

                //death
                if(gp.mob[gp.currentMap][i].hp <= 0){
                    gp.mob[gp.currentMap][i].dying = true;
                    exp += gp.mob[gp.currentMap][i].exp;

                    checkLvlUp();
                }
            }
        }
        else {
            //miss
        }
    }


    public void damageProjectile(int i){
        if(i != 999){

            //hit
            gp.projectile[gp.currentMap][i].alive = false;
            generatPart("gravity", gp.projectile[gp.currentMap][i], gp.projectile[gp.currentMap][i]);
        }
    }



    









    //items
    public void selectItem(){
        int ii = gp.ui.getItemID();

        if(ii < inv.size()){

            
            Entity sitem = inv.get(ii);

            if(sitem.type == type_weapon){
                if(wCount == 0){
                    weapon1 = sitem;
                    lightUpdated = true;
                    attack = getPlayerAttack();
                    def = getPlayerDef();
                    getPlayerAttImage();
                    

                    wCount = 1;
                }
                else if(wCount == 1){
                    weapon2 = sitem;
                    lightUpdated = true;
                    attack = getPlayerAttack();
                    def = getPlayerDef();
                    getPlayerAttImage();
                   

                    wCount = 0;
                }

            }
            if(sitem.type == type_armor){
                armor = sitem;
                def = getPlayerDef();
            }
            if(sitem.type == type_consumable){
                if(sitem.ammount > 1){
                    sitem.use(this);
                    sitem.ammount--;
                }
                else{
                    sitem.use(this);
                    inv.remove(ii);
                }
                
            }
            
        }
    }

    public int searchInvForItem(String itemName){

        int itemI = 999;

        for(int i = 0; i < inv.size(); i++){
            if(inv.get(i).name.equals(itemName)){
                itemI = i;
                break;
            }
        }
        return itemI;
    }

    public boolean canObtainItem(Entity item){
        boolean canObtain = false;

        if(item.stackable == true){
            int index = searchInvForItem(item.name);

            if(index != 999){
                inv.get(index).ammount++;
                canObtain = true;
            }
            else {
                if(inv.size() != maxInvSize){
                    inv.add(item);
                    canObtain = true;
                }
            }
        }
        else {
            if(inv.size() != maxInvSize){
                inv.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }

    public void buyItem(){
        int ii = gp.ui.getItemID();

        if(ii < gp.ui.npc.inv.size()){

            
            Entity sitem = gp.ui.npc.inv.get(ii);

            inv.add(sitem);
            gp.ui.npc.inv.remove(ii);
        }
    }

    public void sellItem(){
        int ii = gp.ui.getItemID();

        if(ii < inv.size()){

            
            Entity sitem = inv.get(ii);

            gp.ui.npc.inv.add(sitem);
            inv.remove(sitem);
        }
    }









    //other

    public void interractNPC(int i){
        if(i != 999){
            if(keyH.dialoguePressed){
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void checkLvlUp(){
        if(exp >= toNextLvl){
            lvl++;
            toNextLvl = toNextLvl*2;
            maxhp += 10;
            hp = maxhp;

            gp.playSFX(0);
            gp.ui.showMessage("You feel stronger...");
        }
    }





    public void dodge(){

            dCount++;

            if(dCount == 1){
                endurance-=10;
            }

            switch(direction){
                case "up":
                    worldY -= speed*2;
                    break;
                case "down":
                    worldY += speed*2;
                    break;
                case "right":
                    worldX += speed*2;
                    break;
                case "left":
                    worldX -= speed*2;
                    break;
            }

            if(dCount == dodgeLength){
                dCount = 0;
                keyH.dodgePressed = false;
            }
        
    }


    public void parry(){

        pCount++;
        isParrying = true;

        if(pCount == 1){
            endurance-=25;
            speed = 0;
        }

        

        if(pCount == parryLength || endurance<=0){
            speed = defaultSpeed;
            keyH.parryPressed = false;
            isParrying = false;
            pCount = 0;
        }
    
    }






    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScrX = screenX;
        int tempScrY = screenY;

        if(isFalling == true){
            changeAlpha(g2, 1f);
        }

        switch(direction) {
            case "up":
                if(attacking == false){
                    if(spriteNum == 0){image = anim[0];}
                    if(spriteNum == 1){image = anim[1];}
                }
            	if(attacking == true) {
                    tempScrY = screenY-gp.tileSize;
                    if(spriteNum == 0){image = anim[8];}
                    if(spriteNum == 1){image = anim[9];}
                }
                break;

            case "down":
                if(attacking == false){
                    if(spriteNum == 0){image = anim[2];}
                    if(spriteNum == 1){image = anim[3];}
                }
                if(attacking == true) {
                    if(spriteNum == 0){image = anim[10];}
                    if(spriteNum == 1){image = anim[11];}
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 0){image = anim[4];}
                    if(spriteNum == 1){image = anim[5];}
                }
                if(attacking == true) {
                    tempScrX = screenX-gp.tileSize;
                    if(spriteNum == 0){image = anim[12];}
                    if(spriteNum == 1){image = anim[13];}
                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 0){image = anim[6];}
                    if(spriteNum == 1){image = anim[7];}
                }
                if(attacking == true){
                    if(spriteNum == 0){image = anim[14];}
                    if(spriteNum == 1){image = anim[15];}
                }
                break;
        }






        //ANIMS
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        if(sleeps == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }

        if(drawing == true){
            g2.drawImage(image, tempScrX, tempScrY, null);
        }
        
        if(sleeps == false){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }






    public Color getParticleColor(){
        Color c = Color.orange
        ;
        return c;
    }
    public int getPartSize(){
        int size = 20;
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
