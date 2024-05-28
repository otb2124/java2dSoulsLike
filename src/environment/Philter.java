package environment;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

public class Philter {
    
    GamePanel gp;
    BufferedImage darknessFilter;
    public boolean isSet = false;
    float alpha = 1f;
    boolean isComplete;
    Color cc;

    public final int type_lightphilter = 0, type_mediumphilter = 1, type_hardphilter = 2, type_pulse = 3;
    int type;

    public Philter(GamePanel _gp){
        gp = _gp;
    }

    public void setPhilter(int _type, Color c, float a, boolean _isComplete){

        isComplete = _isComplete;
        alpha = a;
        type = _type;
        cc = new Color(c.getRed(), c.getGreen(), c.getBlue(), Math.round(a*255));

        if(isSet == false){

                darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeigth, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

                float centerX = gp.player.screenX + (gp.tileSize)/2;
                float centerY = gp.player.screenY + (gp.tileSize)/2;
    
                //gradient
                int i = 12;
                Color color[] = new Color[i];
                float fraction[] = new float[i];
    
                color[0] = new Color(0, 0, 0, 0.1f);
                color[1] = new Color(0, 0, 0, 0.42f);
                color[2] = new Color(0, 0, 0, 0.52f);
                color[3] = new Color(0, 0, 0, 0.61f);
                color[4] = new Color(0, 0, 0, 0.69f);
                color[5] = new Color(0, 0, 0, 0.76f);
                color[6] = new Color(0, 0, 0, 0.82f);
                color[7] = new Color(0, 0, 0, 0.87f);
                color[8] = new Color(0, 0, 0, 0.91f);
                color[9] = new Color(0, 0, 0, 0.94f);
                color[10] = new Color(0, 0, 0, 0.96f);
                color[11] = new Color(0, 0, 0, 0.98f);

                for(int j = 0; j<i; j++){
                    int al = color[j].getAlpha();
                    color[j] = new Color(c.getRed(), c.getGreen(), c.getBlue(), al);
                }
    
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

                int rad = 0;
    
                switch(_type){
                    case type_lightphilter: rad = 2000; break;
                    case type_mediumphilter: rad = 1000; break;
                    case type_hardphilter: rad = 900; break;
                    case type_pulse: rad = 500; break;
                }
                
                RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (rad/2), fraction, color);
                g2.setPaint(gPaint);
                
                g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeigth2);
                g2.dispose();

                isSet = true;
        }
    }

        

    

    public void draw(Graphics2D g2){

        if(isSet == true){
            if(isComplete == true){
                g2.setColor(cc);
                g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeigth2);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(darknessFilter, 0, 0, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        
    }


    public void end(){
        isSet = false;
    }
}