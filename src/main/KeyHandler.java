package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, debugMode, dialoguePressed, attackPressed, manaPressed, dodgePressed, parryPressed;




    public KeyHandler(GamePanel _gp){
        gp = _gp;
    }




    public void keyTyped(KeyEvent e) {
        
        
    }







    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
    
            if(code == KeyEvent.VK_T) {
                if(debugMode == true){
                    debugMode = false;
                }
                else{
                    debugMode = true;
                }
                
            }

            if(code == KeyEvent.VK_E){
                gp.ui.pauseScreenState = 0;
                gp.gameState = gp.pauseState;
            }



            if(code == KeyEvent.VK_ENTER) {
                dialoguePressed = true;
            }


            if(code == KeyEvent.VK_SPACE){
                attackPressed = true;
            }


            if(code == KeyEvent.VK_F){
                manaPressed = true;
            }

            if(code == KeyEvent.VK_M){
                gp.gameState = gp.mapState;
            }
           

            if(code == KeyEvent.VK_SHIFT){
                dodgePressed = true;
            }

            if(code == KeyEvent.VK_K){
                parryPressed = true;
            }
        }

        else if(code == KeyEvent.VK_ESCAPE && gp.gameState == gp.mapState){
            gp.gameState = gp.playState;
        }





        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
                }
        }
        




        if(gp.gameState == gp.pauseState){
            
            if(gp.ui.pauseScreenState == 0){
                if(code == KeyEvent.VK_W){
                
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 4;
                    }
                }
                if(code == KeyEvent.VK_S){
    
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 4){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ESCAPE){
                    gp.gameState = gp.playState;
                }
                if(code == KeyEvent.VK_ENTER){
    
                    if(gp.ui.commandNum == 0){
                        gp.ui.pauseScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        gp.ui.pauseScreenState = 2;
                    }
                    if(gp.ui.commandNum == 2){
                        gp.ui.pauseScreenState = 3;
                    }
                    if(gp.ui.commandNum == 3){
                        gp.ui.pauseScreenState = 4;
                    }
                    if(gp.ui.commandNum == 4){
                        gp.ui.pauseScreenState = 5;
                    }
                }
            }



            //INV
            if(gp.ui.pauseScreenState == 1){
                if(code == KeyEvent.VK_ESCAPE){
                    gp.ui.pauseScreenState = 0;
                }
                if(code == KeyEvent.VK_W){
                    if(gp.ui.slotRow != 0){
                        gp.ui.slotRow--;
                    }
                }
                if(code == KeyEvent.VK_S){
                    if(gp.ui.slotRow != 3){
                        gp.ui.slotRow++;
                    }
                }
                if(code == KeyEvent.VK_D){
                    if(gp.ui.slotCol != 13){
                        gp.ui.slotCol++;
                    }
                }
                if(code == KeyEvent.VK_A){
                    if(gp.ui.slotCol != 0){
                        gp.ui.slotCol--;
                    }  
                }

                if(code == KeyEvent.VK_ENTER){
                    gp.player.selectItem();
                }
            }


            //STAT
            if(gp.ui.pauseScreenState == 2){
                if(code == KeyEvent.VK_ESCAPE){
                    gp.ui.pauseScreenState = 0;
                }
            }
            //opt
            if(gp.ui.pauseScreenState == 3){
                if(code == KeyEvent.VK_ESCAPE){
                    gp.ui.pauseScreenState = 0;
                }
                if(code == KeyEvent.VK_W){
                
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 4;
                    }
                }
                if(code == KeyEvent.VK_S){
    
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 4){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ESCAPE){
                    gp.gameState = gp.playState;
                }

                if(gp.ui.commandNum == 1){
                    if(code == KeyEvent.VK_A && gp.ost.volumeScale > 0){
                        gp.ost.volumeScale--;
                        gp.ost.checkVolume();
                    }
                    if(code == KeyEvent.VK_D && gp.ost.volumeScale < 5){
                        gp.ost.volumeScale++;
                    }
                }

                if(gp.ui.commandNum == 2){
                    if(code == KeyEvent.VK_A && gp.sfx.volumeScale > 0){
                        gp.sfx.volumeScale--;
                        gp.sfx.checkVolume();
                        gp.playSFX(0);
                    }
                    if(code == KeyEvent.VK_D && gp.sfx.volumeScale < 5){
                        gp.sfx.volumeScale++;
                        gp.playSFX(0);
                    }
                }

                if(code == KeyEvent.VK_ENTER){
    
                    if(gp.ui.commandNum == 0){
                        if(gp.fullScreenOn == false){
                            gp.fullScreenOn = true;
                        }
                       else{
                            gp.fullScreenOn = false;
                       }
                       dialoguePressed = false;
                    }
                    
                    if(gp.ui.commandNum == 3){
                        gp.ui.controlState = true;
                        
                    }
                    if(gp.ui.commandNum == 4){
                        System.exit(0);
                    }
                }
            }
            if(gp.ui.pauseScreenState == 4){
                if(code == KeyEvent.VK_ESCAPE){
                    gp.ui.pauseScreenState = 0;
                }
            }
            if(gp.ui.pauseScreenState == 5){
                if(code == KeyEvent.VK_ESCAPE){
                    gp.ui.pauseScreenState = 0;
                }
            }
        }



        if(gp.gameState == gp.deathState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;

                gp.player.setDefaultValues();
                gp.as.setMob();
                gp.as.setNPC();
            }
        }



        if(gp.gameState == gp.tradeState){
            if(gp.ui.tradeScreenState == 0){
                if(code == KeyEvent.VK_W){
                
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S){
    
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
    
                    if(gp.ui.commandNum == 0){
                        gp.ui.tradeScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        gp.ui.tradeScreenState = 2;
                    }
                    if(gp.ui.commandNum == 2){
                        gp.gameState = gp.dialogueState;
                        gp.ui.currentDialog = "Come again soon";
                    }
                }
            }
            if(gp.ui.tradeScreenState == 1){
                if(code == KeyEvent.VK_ESCAPE){
                    gp.ui.tradeScreenState = 0;
                }
                if(code == KeyEvent.VK_W){
                    if(gp.ui.slotRow != 0){
                        gp.ui.slotRow--;
                    }
                }
                if(code == KeyEvent.VK_S){
                    if(gp.ui.slotRow != 3){
                        gp.ui.slotRow++;
                    }
                }
                if(code == KeyEvent.VK_D){
                    if(gp.ui.slotCol != 13){
                        gp.ui.slotCol++;
                    }
                }
                if(code == KeyEvent.VK_A){
                    if(gp.ui.slotCol != 0){
                        gp.ui.slotCol--;
                    }  
                }

                if(code == KeyEvent.VK_ENTER){
                    gp.player.buyItem();
                }
            }
            if(gp.ui.tradeScreenState == 2){
                if(code == KeyEvent.VK_ESCAPE){
                    gp.ui.tradeScreenState = 0;
                }
                if(code == KeyEvent.VK_W){
                    if(gp.ui.slotRow != 0){
                        gp.ui.slotRow--;
                    }
                }
                if(code == KeyEvent.VK_S){
                    if(gp.ui.slotRow != 3){
                        gp.ui.slotRow++;
                    }
                }
                if(code == KeyEvent.VK_D){
                    if(gp.ui.slotCol != 13){
                        gp.ui.slotCol++;
                    }
                }
                if(code == KeyEvent.VK_A){
                    if(gp.ui.slotCol != 0){
                        gp.ui.slotCol--;
                    }  
                }

                if(code == KeyEvent.VK_ENTER){
                    gp.player.sellItem();
                }
            }
        }





        if(gp.gameState == gp.titleState){

            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_W){
                
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S){
    
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
    
                    if(gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        //load
                    }
                    if(gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }

            if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W){
                
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S){
    
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 4){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
    
                    if(gp.ui.commandNum == 0){
                        
                    }
                    if(gp.ui.commandNum == 1){
                        
                    }
                    if(gp.ui.commandNum == 2){
                        
                    }
                    if(gp.ui.commandNum == 3){
                        gp.gameState = gp.playState;;
                    }
                    if(gp.ui.commandNum == 4){
                        gp.ui.titleScreenState = 0;
                    }
                }
            }

            
        }

        
    }







    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            dialoguePressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            attackPressed = false;
        }
        if(code == KeyEvent.VK_F){
            manaPressed = false;
        }
    }
    
}
