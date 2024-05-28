package object;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

public class Chest extends Entity{

    GamePanel gp;
    
    public Chest(GamePanel _gp){
        super(_gp);
        gp = _gp;
        
        name = "Chest";
        collision = true;
        openable = true;
        pickable = false;
        anim[0] = setup("/res/obj/chest.png", gp.tileSize, gp.tileSize);
        anim[1] = setup("/res/obj/chest1.png", gp.tileSize, gp.tileSize);
    }


    public boolean checkForKey(Entity entity) {
        
        boolean isCorrect = false;


        for (int i = 0; i < gp.player.inv.size(); i++) {
            if(gp.player.inv.get(i) instanceof Key_Silver){
                if(gp.player.inv.get(i).ammount > 1){
                    gp.player.inv.get(i).ammount--;
                }
                else{
                    gp.player.inv.remove(i);
                }
                isCorrect = true;
            }
        }
        

        return isCorrect;
    }

    public Color getParticleColor(){
        Color c = Color.GRAY;
        return c;
    }
    public int getPartSize(){
        int _size = 6;
        return _size;
    }
    public int getParticleSpeed(){
        int partspeed = 2;
        return partspeed;
    }
    public int getPartMaxHp(){
        int maxLife = 15;
        return maxLife;
    }
}
