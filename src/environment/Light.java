package environment;

import main.GamePanel;
import object.Lantern;
import object.Torch;

import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

public class Light {
    
    GamePanel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    float filterAlpha = 0f;
    public final int day = 0, dusk = 1, night = 2, dawn = 3;
    public int dayState = day;

    public final int dayCycleSpeedDefault = 12000;
    public int dayCycleSpeed = dayCycleSpeedDefault;
    public final float dayCycleFloatDefault = 0.0001f;
    public float dayCycleFloat = dayCycleFloatDefault;

    public int date = 1;

    public Light(GamePanel _gp){
        gp = _gp;
        setLightSource();
    }

    public void setLightSource(){
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeigth, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

        
        
            if(gp.player.weapon1 instanceof Lantern == false && gp.player.weapon2 instanceof Lantern == false && gp.player.weapon1 instanceof Torch == false && gp.player.weapon2 instanceof Torch == false){
                g2.setColor(new Color(0, 0, 0.1f, 0.98f));
            }
            else {
                float centerX = gp.player.screenX + (gp.tileSize)/2;
                float centerY = gp.player.screenY + (gp.tileSize)/2;
    
                //gradient
                int i = 12;
                Color color[] = new Color[i];
                float fraction[] = new float[i];
    
                color[0] = new Color(0, 0, 0.1f, 0.1f);
                color[1] = new Color(0, 0, 0.1f, 0.42f);
                color[2] = new Color(0, 0, 0.1f, 0.52f);
                color[3] = new Color(0, 0, 0.1f, 0.61f);
                color[4] = new Color(0, 0, 0.1f, 0.69f);
                color[5] = new Color(0, 0, 0.1f, 0.76f);
                color[6] = new Color(0, 0, 0.1f, 0.82f);
                color[7] = new Color(0, 0, 0.1f, 0.87f);
                color[8] = new Color(0, 0, 0.1f, 0.91f);
                color[9] = new Color(0, 0, 0.1f, 0.94f);
                color[10] = new Color(0, 0, 0.1f, 0.96f);
                color[11] = new Color(0, 0, 0.1f, 0.98f);
    
                fraction[0] = 0f;
                fraction[1] = 0.4f;
                fraction[2] = 0.5f;
                fraction[3] = 0.6f;
                fraction[4] = 0.65f;
                fraction[5] = 0.7f;
                fraction[6] = 0.75f;
                fraction[7] = 0.8f;
                fraction[8] = 0.85f;
                fraction[9] = 0.9f;
                fraction[10] = 0.95f;
                fraction[11] = 1f;
    
                int rad;
                if(gp.player.weapon1 instanceof Lantern || gp.player.weapon1 instanceof Torch){
                    rad = gp.player.weapon1.emissionRadius;
                }
                else {
                    rad = gp.player.weapon2.emissionRadius;
                }
    
                RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (rad/2), fraction, color);
                g2.setPaint(gPaint);
            }

            g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeigth2);
        
        
        

        
        g2.dispose();
        
    }
    public void update(){

        if(gp.player.lightUpdated == true){
            setLightSource();
            gp.player.lightUpdated = false;
        }



        
        

            if(dayState == day){
                dayCounter++;
    
                if(dayCounter > dayCycleSpeed){
                    dayState = dusk;
                    dayCounter = 0;
                }
    
            }
            if(dayState == dusk){
                filterAlpha+=dayCycleFloat;
    
                if(filterAlpha > 1f){
                    filterAlpha = 1f;
                    dayState = night;
                }
            }
            if(dayState == night){
                dayCounter++;
    
                if(dayCounter > dayCycleSpeed){
                    dayState = dawn;
                    dayCounter = 0;
                }
            }
            if(dayState == dawn){
                filterAlpha-=dayCycleFloat;
    
                if(filterAlpha < 0f){
                    filterAlpha = 0f;
                    date++;
                    dayState = day;
                }
            }
        }


        

    

    public void draw(Graphics2D g2){

        //cycle
        if(gp.currentMap == 0 || gp.currentMap == 1){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }

        //dark + cycle
        if(gp.currentMap == 3                 || gp.currentMap == 0 || gp.currentMap == 1){
            g2.drawImage(darknessFilter, 0, 0, null);
        }
        
        //else is light
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
    }
}