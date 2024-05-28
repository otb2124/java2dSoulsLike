package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;
import java.awt.image.BufferedImage;

public class Particle extends Entity{

    GamePanel gp;

    Entity generator;
    Color c;
    BufferedImage img;
    int size, sizeY = size;
    int xd;
    int yd;
    String type;
    float alpha = 1f;

    int state = 0;

    public Particle(String _type, GamePanel _gp, Entity gen, Color color, int _size, int _speed, int maxHP, int _xd, int _yd) {
        super(_gp);
        gp = _gp;

        generator = gen;
        c = color;
        img = null;
        size = _size;
        speed = _speed;
        maxhp = maxHP;
        xd = _xd;
        yd = _yd;
        type = _type;

        hp = maxhp;

        int offset = (gp.tileSize/2) - (size/2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }

    public Particle(String _type, GamePanel _gp, Entity gen, Color color, int _size, int _speed, int maxHP, int _xd, int _yd, int wx, int wy) {
        super(_gp);
        gp = _gp;

        generator = gen;
        c = color;
        img = null;
        size = _size;
        speed = _speed;
        maxhp = maxHP;
        xd = _xd;
        yd = _yd;
        type = _type;

        hp = maxhp;

        int offset = (gp.tileSize/2) - (size/2);
        worldX = generator.worldX + offset + wx;
        worldY = generator.worldY + offset + wy;
    }


    public Particle(String _type, GamePanel _gp, Entity gen, BufferedImage _img, int _size, int _speed, int maxHP, int _xd, int _yd, int wx, int wy) {
        super(_gp);
        gp = _gp;

        generator = gen;
        img = _img;
        c = null;
        size = _size;
        speed = _speed;
        maxhp = maxHP;
        xd = _xd;
        yd = _yd;
        type = _type;

        hp = maxhp;

        int offset = (gp.tileSize/2) - (size/2);
        worldX = generator.worldX + offset + wx;
        worldY = generator.worldY + offset + wy;
    }

    


    public void update(){

        hp--;

        if(hp < maxhp/3){
            switch(type){
                case "gas": yd--; break;
                case "gravity": yd++; break;
                case "dust": break;
                case "rain": break; 
            }
        }
       
        if(type.equals("dust")){
            size+=2;
            alpha-=0.05f;
            yd--;
            worldX += xd*speed*2;
            worldY += yd*speed/2;
        }
        else if(type.equals("rain")){

            if(gp.em.rain.type == gp.em.rain.type_wind){
                switch(gp.em.rain.windDirection){
                    case "up": worldY-=speed; break;
                    case "down": worldY+=speed; break;
                    case "left": worldX-=speed; break;
                    case "right": worldX+=speed; break;
                }
            }
            else{
                worldX-=speed;
                worldY+=speed;
            }
            
            if(gp.em.rain.type == gp.em.rain.type_rain_default || gp.em.rain.type == gp.em.rain.type_rain_storm){
                sizeY = size*3;
            }
            else if(gp.em.rain.type == gp.em.rain.type_wind){
                if(gp.em.rain.windDirection.equals("up") || gp.em.rain.windDirection.equals("down")){
                    sizeY = size*20;
                }
                else {
                    size = sizeY*20;
                }
            }
            else {
                sizeY = size;
            }
        }
        else if(type.equals("fog")){
            worldX += speed;
        }
        else{
            worldX += xd*speed;
            worldY += yd*speed;
            sizeY = size;
        }

        if(hp == 0){
            alive = false;
        }
    }
    public void draw(Graphics2D g2){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(type.equals("dust")){
            changeAlpha(g2, alpha);
            sizeY = size;
        }
        if(c != null){
            g2.setColor(c);

            g2.fillRect(screenX, screenY, size, sizeY);
        }
        else if(img != null){
            g2.drawImage(img, screenX, screenY, null);
        }
       
    }
}
