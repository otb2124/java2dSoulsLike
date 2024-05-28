package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    
    GamePanel gp;

    public Config(GamePanel _gp){
        gp = _gp;
    }

    public void saveConfig(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Config.txt"))) {

            if(gp.fullScreenOn == true){
                bw.write("On");
            }
            if(gp.fullScreenOn == false){
                bw.write("Off");
            }
            bw.newLine();

            bw.write(String.valueOf(gp.ost.volumeScale));
            bw.newLine();

            bw.write(String.valueOf(gp.sfx.volumeScale));
            bw.newLine();

            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void loadConfig(){
        try (BufferedReader br = new BufferedReader(new FileReader("Config.txt"))) {

            String s = br.readLine();

            if(s.equals("On")){
                gp.fullScreenOn = true;
            }
            if(s.equals("Off")){
                gp.fullScreenOn = false;
            }

            s = br.readLine();
            gp.ost.volumeScale = Integer.parseInt(s);

            s = br.readLine();
            gp.sfx.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
