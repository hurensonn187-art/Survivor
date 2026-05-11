package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;
import survivor.UtilityTool;
import Entities.Player;

public class Slime extends Entity {

    GamePanel gp;


    public Slime(GamePanel gp,int x, int y) {

        this.gp = gp;

        worldX = x * gp.tileSize;
        worldY = y * gp.tileSize;
        speed = 2;

        direction="down";


        solidArea = new Rectangle();
        solidArea.x = 5 * gp.scale;
        solidArea.y = 8 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 12 * gp.scale;
        solidArea.height = 15 * gp.scale;

        getSlimeImage();
    }

    public void moveSlime(){

        double dx = gp.player.worldX - worldX;
        double dy = gp.player.worldY - worldY;


        double distance = Math.sqrt(dx * dx + dy * dy); //sorgt dafür das diagonales Laufen
                                                        //nicht schneller als Horizontales ist
        if(distance != 0) {
            dx /= distance;
            dy /= distance;

            worldX += dx * speed;
            worldY+= dy * speed;
        }

        if(Math.abs(dx) > Math.abs(dy)) {

            if(dx > 0) direction = "right";
            else direction = "left";

        } else {

            if(dy > 0) direction = "down";
            else direction = "up";
        }

    }

    public void getSlimeImage() {

        up1 = setup("SlimeUp");
        down1 = setup("SlimeDown");
        left1 = setup("SlimeLeft");
        right1 = setup("SlimeRight");
        //System.out.println("klappt schleim");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image2 = null;

        try {

            image2 = ImageIO.read(getClass().getResourceAsStream("/enemies/slime/" + imageName + ".png"));
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hilfe");
        }
        return image2;
    }
    public void draw(Graphics2D g2) {

        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image2 = null;

        switch(direction) {
            case "right":
                    image2 = right1;
                break;
            case "left":
                    image2 = left1;
                break;
            case "up":
                    image2 = up1;
                break;
            case "down":
                    image2 = down1;
                break;
            default:
                image2 = down1; //falls was schiefgeht
                break;
        }

        g2.drawImage(image2,(int)screenX,(int) screenY, null);
        //g2.setColor(Color.GREEN);    //Malt CollisionBox
        //g2.drawRect((int)screenX , (int)screenY, 64, 64);
        //System.out.println(worldX + "   " + worldY);
    }
}