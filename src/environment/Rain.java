package environment;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;

public class Rain {

    GamePanel gp;
    public final int type_rain_default = 0, type_rain_storm = 1, type_snow = 2, type_snowfall = 3, type_ash = 4, type_fog = 5, type_wind = 6;

    int counter = 0;
    int differenceX = 0, differenceY = 0;
    Random r = new Random();
    public boolean changed = false;

    public Color c; public int size, speed, cooldown, rate;
    public int type;

    public String windDirection;
    
    
    public Rain(GamePanel _gp){
        gp = _gp;

        
    }


    public void update(){

            counter++;
            int rn = r.nextInt(100);
            

            //falls
            if(type != type_fog){
                if(counter == 1){
                    for (int i = 0; i < 100; i++) {
                        int ran = r.nextInt(100);
    
                        if(ran < rate){
                            gp.player.generatPart("rain", gp.player, c, size, speed, 360, 750-differenceX, -220-differenceY);
                            
                            //trace
                            if(type == type_snowfall || type == type_wind){
                                for (int j = 1; j < 6; j++) {
                                    Color c1 = new Color(255, 255, 255, 255-40*j);

                                    if(type == type_wind){
                                        switch(windDirection){
                                            case "up": differenceX = differenceX*j*j; break;
                                            case "down": differenceX = differenceX*j*j; break;
                                            case "left": differenceY = differenceY*j*j; break;
                                            case "right": differenceY = differenceY*j*j; break;
                                        }
                                        size = size*j;
                                    }
                                    gp.player.generatPart("rain", gp.player, c1, size, speed, 360, 750-differenceX+size/2*j, -220-differenceY-size/2*j);

                                    if(type == type_wind){
                                        size = size/j;
                                        switch(windDirection){
                                            case "up": differenceX = differenceX/j/j; break;
                                            case "down": differenceX = differenceX/j/j; break;
                                            case "left": differenceY = differenceY/j/j; break;
                                            case "right": differenceY = differenceY/j/j;; break;
                                        }

                                    }
                                }
                            }
                        }
                        
                        differenceX+=10;
    

                        if(type != type_wind){
                            if((i % 3) > 0.5){
                                differenceY = 100;
                            }
                            else if((i % 7) > 0){
                                differenceY = 50;
                            }
                            else if((i % 2) > 0){
                                differenceY = 30;
                            }
                            else{
                                differenceY = 0;
                            }
                        }
                        else{
                            
                            switch(windDirection){
                                case "up": differenceY = -500; break;
                                case "down": differenceY = 1000; break;
                                case "left": break;
                                case "right": break;
                            }

                            if((i % 3) > 0.5){
                                differenceY += -50;
                            }
                            else if((i % 7) > 0){
                                differenceY += -25;
                            }
                            else if((i % 2) > 0){
                                differenceY += -60;
                            }
                            else{
                                
                            }


                            
                        }
                        
    
    
                        //ashsizing
                        if(type == type_ash || type == type_snowfall){
                            if(ran > 0 && ran <= 25){
                                size = 10;
                            }
                            if(ran > 25 && ran <= 50){
                                size = 7;
                            }
                            if(ran > 50 && ran <= 75){
                                size = 5;
                            }
                            if(ran > 75 && ran <= 100){
                                size = 3;
                            }
                        }
                    }
                }
    
                //thunder
                if(counter == cooldown/2 && rn < 3 && type == type_rain_storm){
                    gp.em.bleak.summonBleak(Color.white, 15, true);
                }
            }





            //fog
            else if(type == type_fog){

                rn = r.nextInt(500);
                int resize = (rn/100)+1;

                if(counter == 100){
                    gp.player.generatPart("fog", gp.player, gp.player.setup("/res/particles/fog.png", gp.tileSize*6-resize, gp.tileSize*2-resize), size, -speed, 2400, 750, -220+rn);
                }
                if(counter == 200){
                    gp.player.generatPart("fog", gp.player, gp.player.setup("/res/particles/fog0.png", gp.tileSize*6/resize, gp.tileSize*2/resize), size, speed, 2400, -750, -220+rn);
                }
            }

            
            
            

            if(counter == cooldown){
                counter = 0;
                differenceX = 0;
            }
        
    }


    public void changeTo(int _type){

        if(changed == false){
            switch(_type){
                case type_rain_default: c = new Color(0, 0, 255, 100); rate = 60; cooldown = 25; speed = 6; size = 3; break;
                case type_rain_storm: c = new Color(0, 0, 100); rate = 50; cooldown = 10; size = 3; speed = 8; break;
                case type_snow: c = Color.white; size = 7; rate = 10; cooldown = 30; speed = 3; break;
                case type_snowfall: c = Color.white; size = 10; rate = 25; cooldown = 25; speed = 4; break;
                case type_ash: c = Color.black; size = 10; rate = 2; cooldown = 60; speed = 1; break;
                case type_fog: size = 10; cooldown = 200; speed = 1; break;
                case type_wind: c = new Color(255, 255, 255); rate = 5; cooldown = 5; speed = 20; size = 3; break;
            }

            type = _type;
            changed = true;
        }
        
    }



}
