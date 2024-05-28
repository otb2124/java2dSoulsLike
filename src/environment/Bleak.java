package environment;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

public class Bleak {

    GamePanel gp;
    public boolean overDraw = false;
    Color c; int duration;
    int counter = 0;
    public boolean isRunning = false;
    
    public Bleak(GamePanel _gp){
        gp = _gp;
    }

    public void summonBleak(Color _c, int _duration, boolean isOverDrawn){
        overDraw = isOverDrawn;
        c = _c;
        duration = _duration;
        isRunning = true;
    }


    public void draw(Graphics2D g2){
        if(isRunning == true){
            counter++;


            if(overDraw == true){
                if(counter > 0 && counter <= 5){
                    g2.setColor(new Color(counter, counter, counter));
                }
                if(counter > 5 && counter <= duration-5){
                    g2.setColor(c);
                }
                if(counter > duration-5 && counter < duration){
                    g2.setColor(new Color(duration-counter, duration-counter, duration-counter));
                }
            }
            else {
                g2.setColor(c);
            }
            
            
            g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeigth2);

            if(counter == duration){
                isRunning = false;
                counter = 0;
                g2.clearRect(0, 0, gp.screenWidth2, gp.screenHeigth2);
            }
        }
    }
}