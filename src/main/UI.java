package main;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import entity.Entity;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BasicStroke;

public class UI {
    
    KeyHandler kh;
    GamePanel gp;
    Graphics2D g2;
    public Font chiller, viner;


    public boolean messageOn = false;
    public boolean pickupMessageOn = false;
    public String message = "";
    int messagecounter = 0;

    public String currentDialog = "";

    public int commandNum = 0;

    public int titleScreenState = 0; //0 title, 1 character, 2 settings
    public int pauseScreenState = 0; //0 pause 1 inv 2 stats 3 settings 4 quit
    public int tradeScreenState = 0;
    public boolean controlState = false;

    public int slotCol = 0;
    public int slotRow = 0;

    public int traderSlotCol = 0;
    public int traderSlotRow = 0;

    public Entity npc;





    public UI(GamePanel _gp){
        gp = _gp;

        
        try {
            InputStream is = getClass().getResourceAsStream("/res/font/VINERITC.TTF");
            viner = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/font/CHILLER.TTF");
            chiller = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;

    }
    public void pickupShowMessage(String text, Entity object){
        message = text;
        pickupMessageOn = true;
    }

    public void draw(Graphics2D _g2){

        g2 = _g2;


        //message
        if(messageOn == true){
            g2.setColor(Color.white);
            g2.setFont(chiller);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
            g2.drawString(message, getXCenter(message), gp.tileSize*10);
            messagecounter++;

            if(messagecounter > 120){
                messagecounter = 0;
                messageOn = false;
            }
        }

        if(pickupMessageOn == true){
            g2.setColor(Color.white);
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
            g2.drawString(message, getXCenter(message), gp.tileSize*10);

            //hmmmmmmmmmmmmmmmmmmmmmmmmmmmm
        }

        //STATES

        if(gp.gameState == gp.playState){
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.deathState){
            drawGameOverScreen();
        }
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
        if(gp.gameState == gp.mapState){
            gp.worldMap.drawFullMap(g2);
        }





        if(gp.gameState != gp.titleState && gp.gameState != gp.dialogueState && gp.gameState != gp.mapState){
            //highbar
            drawHpBar();
            drawEndurBar();
            drawManaBar();

            if(gp.player.onRopeWay == true){
                drawRopeWayBalance();
            }
        }
        
    }



    public void drawPauseScreen(){
        final int frameX = gp.tileSize/2;
        final int frameY = gp.tileSize;
        final int frameW = gp.tileSize*2-gp.tileSize/2;
        final int frameH = gp.tileSize*7;


         //MAIN
        if(pauseScreenState == 0){
            drawSubWindow(frameX, frameY, frameW, frameH);

            g2.setColor(Color.white);
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));

            if(commandNum == 0){
                g2.drawString("<", frameX*4, frameY*2);
            }
            if(commandNum == 1){
                g2.drawString("<", frameX*4, frameY*3);
            }
            if(commandNum == 2){
                g2.drawString("<", frameX*4, frameY*4);
            }
            if(commandNum == 3){
                g2.drawString("<", frameX*4, frameY*5);
            }
            if(commandNum == 4){
                g2.drawString("<", frameX*4, frameY*6);
            }
            
        }

        //STATS
        if(pauseScreenState == 2){
            drawSubWindow(frameX, frameY, frameH*2+gp.tileSize, frameH);

            g2.setColor(Color.white);
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));

            g2.setColor(Color.white);
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
            int textX = frameX + 10;
            int textY = frameY + gp.tileSize*2/3;
            final int lineHeigt = 18;


            g2.drawString("Character Stats", textX+gp.tileSize+gp.tileSize/2, textY);
            textY += lineHeigt;
            g2.drawString("Level: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Health: ", textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            g2.drawString("Strength: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Dexterity: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Will Power: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Intellect: ", textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            g2.drawString("Attack: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Defense: ", textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            g2.drawString("Exp: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Exp to Next Level: ", textX, textY);
            textY += lineHeigt;

            textX += gp.tileSize*8;
            textY = frameY + gp.tileSize*2/3;

            g2.drawString("Equipment Stats", textX+gp.tileSize, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            g2.drawString("Weapon 1", textX+gp.tileSize/2, textY);
            textY += lineHeigt;
            g2.drawString("Name: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Damage: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Defense: ", textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            g2.drawString("Weapon 2", textX+gp.tileSize/2, textY);
            textY += lineHeigt;
            g2.drawString("Name: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Damage: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Defense: ", textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            g2.drawString("Armor", textX+gp.tileSize/2, textY);
            textY += lineHeigt;
            g2.drawString("Name: ", textX, textY);
            textY += lineHeigt;
            g2.drawString("Defense: ", textX, textY);
            

            int tailX = (frameX+frameW) + 200;
            textY = frameY + gp.tileSize*2-gp.tileSize/2;
            String value;

            value = String.valueOf(gp.player.lvl);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.hp) + "/" + String.valueOf(gp.player.maxhp);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            value = String.valueOf(gp.player.strength);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.dexerity);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.will);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.intellect);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            value = String.valueOf(gp.player.attack);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.def);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            value = String.valueOf(gp.player.exp);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.toNextLvl);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);

            tailX += gp.tileSize*8;
            textY = frameY + gp.tileSize*2-gp.tileSize/3;

            textY += lineHeigt;
            textY += lineHeigt;
            value = String.valueOf(gp.player.weapon1.name);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.weapon1.dmg);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.weapon1.protection);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            textY += lineHeigt;
            value = String.valueOf(gp.player.weapon2.name);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.weapon2.dmg);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.weapon2.protection);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
            textY += lineHeigt;
            value = String.valueOf(gp.player.armor.name);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            value = String.valueOf(gp.player.armor.protection);
            textX = getXForAlignToRight(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeigt;
            textY += lineHeigt;
        }







        //INV
        if(pauseScreenState == 1){
            drawInventory(gp.player, true);
        }


        //options
        if(pauseScreenState == 3){
            drawSubWindow(frameX, frameY, frameH+frameH/2, frameH);

            g2.setColor(Color.white);
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));

            //fullscr
            g2.drawString("Full screen mode", frameX*4, frameY*2);
            if(commandNum == 0){
                g2.drawString("<", frameX*12, frameY*2);
            }
            g2.drawString("Music", frameX*4, frameY*3);
            if(commandNum == 1){
                g2.drawString("<", frameX*12, frameY*3);
            }
            g2.drawString("SFX", frameX*4, frameY*4);
            if(commandNum == 2){
                g2.drawString("<", frameX*12, frameY*4);
            }
            g2.drawString("Control", frameX*4, frameY*5);
            if(commandNum == 3){
                g2.drawString("<", frameX*12, frameY*5);
            }
            g2.drawString("End Game", frameX*4, frameY*6);
            if(commandNum == 4){
                g2.drawString("<", frameX*12, frameY*6);
            }

            g2.setStroke(new BasicStroke(3));
            g2.drawRect(frameX*11, frameY + 30, 24, 24);
            if(gp.fullScreenOn == true){
                g2.fillRect(frameX*11, frameY + 30, 24, 24);
            }

            g2.drawRect(frameX*11, frameY*2 + 30, 100, 24);
            int volumeW = 20 * gp.ost.volumeScale;
            g2.fillRect(frameX*11, frameY*2 + 30, volumeW, 24);

            g2.drawRect(frameX*11, frameY*3 + 30, 100, 24);
            int sfxeW = 20 * gp.sfx.volumeScale;
            g2.fillRect(frameX*11, frameY*3 + 30, sfxeW, 24);


            if(controlState == true){
                controlMenu(frameX, frameY, frameH, frameH);
            }

            gp.config.saveConfig();
        }
        if(pauseScreenState == 4){
            drawSubWindow(frameX, frameY, frameH+frameH/2, frameH);

            g2.setColor(Color.white);
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));

            g2.drawString("Back <", frameX*4, frameY*10);
        }
        if(pauseScreenState == 5){
            drawSubWindow(frameX, frameY, frameH+frameH/2, frameH);

            g2.setColor(Color.white);
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));

            g2.drawString("Back <", frameX*4, frameY*10);
        }
        


        
    }


    public void drawTradeScreen(){

        switch(tradeScreenState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.dialoguePressed = false;
    }

    public void trade_select(){
        drawDialogueScreen();

        drawSubWindow(gp.tileSize*10, gp.tileSize*4, gp.tileSize*4, gp.tileSize*7);

        g2.drawString("Buy", gp.tileSize*10, gp.tileSize*5);
        if(commandNum == 0){
            g2.drawString("<", gp.tileSize*11, gp.tileSize*5);
        }
        g2.drawString("Sell", gp.tileSize*10, gp.tileSize*6);
        if(commandNum == 1){
            g2.drawString("<", gp.tileSize*11, gp.tileSize*6);
        }
        g2.drawString("Quit", gp.tileSize*10, gp.tileSize*7);
        if(commandNum == 2){
            g2.drawString("<", gp.tileSize*11, gp.tileSize*7);
        }

    }

    public void trade_buy(){
        drawInventory(gp.player, false);
        drawInventory(npc, true);
    }

    public void trade_sell(){
        drawInventory(gp.player, true);
        drawInventory(npc, false);
    }

    public void drawGameOverScreen(){

        g2.setColor(new Color(0, 0, 0, 150));
        g2.drawRect(0, 0, gp.screenWidth2, gp.screenHeigth2);

        int x;
        int y;
        String text;
        g2.setFont(chiller.deriveFont(Font.BOLD, 110f));

        text="you died";
        g2.setColor(Color.black);
        x = getXCenter(text);
        y = gp.tileSize*4;
        g2.drawString(text, x+4, y+4);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(chiller.deriveFont(Font.BOLD, 50f));
        text = "Retry";
        x = getXCenter(text);
        g2.drawString(text, x, y+gp.tileSize*6);
    }



    public void drawTitleScreen() {

        if(titleScreenState == 0){
            //BG
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeigth2);

            //TITLE TXT
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
            
            String text = "Game Title Name";

            int x = getXCenter(text);
            int y = gp.tileSize*3;
            
                //SHADOW
                g2.setColor(Color.gray);
                g2.drawString(text, x+1, y+1);


                //draw title text
                g2.setColor(Color.white);
                g2.drawString(text, x, y);

            //IMAGE
            x = gp.screenWidth2/2 - (gp.tileSize*2)/2;
            y += gp.tileSize;
            g2.fillRect(x+(gp.tileSize - gp.tileSize/2)/2, y, gp.tileSize*2 - gp.tileSize/2, gp.tileSize*2);
            g2.drawImage(gp.player.anim[3], x, y, gp.tileSize*2, gp.tileSize*2, null);


            //MENU
            g2.setFont(chiller);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));

            text = "New Game";
            x = getXCenter(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Load Game";
            x = getXCenter(text);
            y += gp.tileSize*1.5;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Quit";
            x = getXCenter(text);
            y += gp.tileSize*1.5;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }

        if(titleScreenState == 1){

            //BG
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeigth2);

            //TITLE TXT
            g2.setFont(viner);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
            
            String text = "Character Editor";

            int x = getXCenter(text);
            int y = gp.tileSize*3;
            
                //SHADOW
                g2.setColor(Color.gray);
                g2.drawString(text, x+1, y+1);


                //draw title text
                g2.setColor(Color.white);
                g2.drawString(text, x, y);

            //IMAGE
            x = gp.screenWidth2/2 - (gp.tileSize*2)/2;
            y += gp.tileSize;
            g2.fillRect(x+(gp.tileSize - gp.tileSize/2)/2, y, gp.tileSize*2 - gp.tileSize/2, gp.tileSize*2);
            g2.drawImage(gp.player.anim[3], x, y, gp.tileSize*2, gp.tileSize*2, null);


            //MENU
            g2.setFont(chiller);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));

            text = "Head";
            x = getXCenter(text) - gp.tileSize*5;
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Body";
            x = getXCenter(text) - gp.tileSize*5;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Legs";
            x = getXCenter(text) - gp.tileSize*5;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Continue";
            x = getXCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Quit";
            x = getXCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 4){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
        }
        
    }






    public void drawDialogueScreen(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth2 - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(viner);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        x += gp.tileSize;
        y += gp.tileSize;
        

        for(String line : currentDialog.split("\n")){
            g2.drawString(currentDialog, x, y);
            y += 40;
        }
    }
    public void drawItemWindow(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth2 - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawAcceptWindow(x, y, width, height);

        g2.setFont(viner);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        x += gp.tileSize;
        y += gp.tileSize;
        

        for(String line : currentDialog.split("\n")){
            g2.drawString(currentDialog, x, y);
            y += 40;
        }
    }





    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 100);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawAcceptWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 100);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }



    public void drawHpBar(){
        int maxWidth = gp.player.maxhp;
        int currentWidth = gp.player.hp;

        int x = gp.tileSize;
        int y = gp.tileSize/2;
        
        g2.setColor(Color.black);
        g2.fillRect(x, y, maxWidth, y/2);
        g2.setColor(Color.red);
        g2.fillRect(x, y, currentWidth, y/2);
    }
    public void drawEndurBar(){
        int maxWidth = gp.player.maxEndurance;
        int currentWidth = gp.player.endurance;

        int x = gp.tileSize;
        int y = gp.tileSize/2;

        g2.setColor(Color.black);
        g2.fillRect(x, y+gp.tileSize/3, maxWidth, y/2);
        g2.setColor(Color.yellow);
        g2.fillRect(x, y+gp.tileSize/3, currentWidth, y/2);
    }
    public void drawManaBar(){
        int maxWidth = gp.player.maxMana;
        int currentWidth = gp.player.mana;

        int x = gp.tileSize;
        int y = gp.tileSize/2;

        g2.setColor(Color.black);
        g2.fillRect(x, y+gp.tileSize*2/3, maxWidth, y/2);
        g2.setColor(new Color(255, 0, 255));
        g2.fillRect(x, y+gp.tileSize*2/3, currentWidth, y/2);
    }
    public void drawRopeWayBalance(){
        int maxWidth = 100;
        int currentWidth = gp.player.ropeWayBalance*5;

        int x = gp.player.screenX+gp.tileSize/2;
        int y = gp.player.screenY-gp.tileSize;

        g2.setColor(Color.darkGray);
        g2.fillRect(x-maxWidth/2, y, maxWidth, 10);
        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(x, y, currentWidth, 10);

        if(gp.player.ropeWayBalance < 0){
            g2.fillRect(x-Math.abs(currentWidth), y, Math.abs(currentWidth), 10);
        }
    }




    public void drawInventory(Entity entity, boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameH = 0;

        if(entity == gp.player){
            frameX = gp.tileSize/2;
            frameY = gp.tileSize*2-gp.tileSize/2;
            frameH = gp.tileSize*10;
        }
        else {
            frameX = gp.tileSize/2;
            frameY = gp.tileSize*6+gp.tileSize/2;
            frameH = gp.tileSize*10;
        }
        
        //back
        drawSubWindow(frameX, frameY, frameH+frameH/2, frameH/2);
            
        //SLOT 14x4
        final int slotXstart = frameX + 25;
        final int slotYStart = frameY + 25;
        int slotX = slotXstart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize;

        //items
        for (int i = 0; i < entity.inv.size(); i++) {

            //Equip curs
            if(entity.inv.get(i) == entity.weapon1 || entity.inv.get(i) == entity.weapon2 || entity.inv.get(i) == entity.armor){
                g2.setColor(Color.CYAN);
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inv.get(i).anim[0], slotX, slotY, null);
            if(entity.inv.get(i).ammount > 1){
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountx;
                int amounty;

                String a = "" + entity.inv.get(i).ammount;
                amountx = getXForAlignToRight(a, slotX+44);
                amounty = slotY+gp.tileSize;

                g2.setColor(Color.white);
                g2.drawString(a, amountx, amounty);
            }
            slotX+= slotSize;

            if(i == 13 || i == 13*2 || i == 13*3){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        if(cursor == true){
             //cursor
        int cursX = slotXstart + (slotSize*slotCol);
        int cursY = slotYStart + (slotSize*slotRow);
        int cursW = gp.tileSize;
        int cursH = gp.tileSize;

        //drawCurs
        g2.setColor(Color.white);
        g2.drawRoundRect(cursX, cursY, cursW, cursH, 10, 10);


            //desc
            int dFrameX = frameX;
            int dFrameY = frameY+gp.tileSize*5;
            int dFrameW = gp.tileSize*5;
            int dFrameH = gp.tileSize*5;
            

            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(chiller);
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemID();
            if(itemIndex < entity.inv.size() ){

                drawSubWindow(dFrameX, dFrameY, dFrameW, dFrameH);

                g2.drawString(entity.inv.get(itemIndex).name, textX, textY);
                g2.setFont(g2.getFont().deriveFont(20F));

                for(String line : entity.inv.get(itemIndex).descript.split("\n")){
                    g2.drawString(line, textX, textY+gp.tileSize);
                    textY += 40;
                }
            }
        }
       
        }


    public void controlMenu(int x, int y, int w, int h){

        drawSubWindow(x+w/4, y, w, h);

        int textX = x+gp.tileSize*3;
        int textY = y+gp.tileSize;

        g2.drawString("Move", textX, textY); textY += gp.tileSize/2;
        g2.drawString("Interract", textX, textY); textY += gp.tileSize/2;
        g2.drawString("Attack", textX, textY); textY += gp.tileSize/2;
        g2.drawString("Cast", textX, textY); textY += gp.tileSize/2;

        textX+=gp.tileSize*5; textY = y+gp.tileSize;

        g2.drawString("WASD", textX, textY); textY += gp.tileSize/2;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize/2;
        g2.drawString("SPACE", textX, textY); textY += gp.tileSize/2;
        g2.drawString("F", textX, textY); textY += gp.tileSize/2;
    }

    public int getItemID(){
        return slotCol + (slotRow*14);
    }
    public int getXCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth2/2 - length/2;
        return x;
    }
    public int getXForAlignToRight(String text, int tail){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tail - length;
        return x;
    }
}