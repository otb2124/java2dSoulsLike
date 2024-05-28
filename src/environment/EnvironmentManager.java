package environment;

import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;

public class EnvironmentManager {
    
    GamePanel gp;
    public Light light;
    public Rain rain;
    public Bleak bleak;
    public Philter philter;

    public EnvironmentManager(GamePanel _gp){
        gp = _gp;
    }

    public void setup(){
        light = new Light(gp);
        rain = new Rain(gp);
        bleak = new Bleak(gp);
        philter = new Philter(gp);
    }

    public void update(){

        try{
            light.update();
        }
        catch(NullPointerException e){
            System.out.println("java throws nullpointer at EnvMan/update");
        }


        //map conditions
        if(gp.currentMap == 1){
            rain.changeTo(rain.type_fog);
            rain.update();
            philter.setPhilter(philter.type_mediumphilter, new Color(255, 255, 255), 0.5f, true);    
        }
        else if(gp.currentMap == 0){
            if(gp.cm.cutsceneNum != -1){
                philter.setPhilter(philter.type_mediumphilter, new Color(0, 0, 0), 1f, false); 
            }
            else {
                philter.end();
            }
        }
        else if(gp.currentMap == 4){
            rain.changeTo(rain.type_snowfall);
            rain.update();
            philter.setPhilter(philter.type_hardphilter, new Color(100, 100, 255), 1f, false); 
        }
        else if(gp.currentMap == 3){
            rain.changeTo(rain.type_rain_default);
            rain.update();
        }
        else if(gp.currentMap == 2){
            rain.changeTo(rain.type_ash);
            rain.update();
            philter.setPhilter(philter.type_hardphilter, new Color(20, 0, 0), 1f, false); 
        }
        else{
            philter.end();
        }

        
        
    }

    public void draw(Graphics2D g2){
        
        philter.draw(g2);
        light.draw(g2);
    }
    
}
