package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import survivor.GamePanel;
import survivor.UtilityTool;

public class Slime extends Entity {

    GamePanel gp;

    public Slime(GamePanel gp) {

        this.gp = gp;

        worldX = 24 * gp.tileSize;
        worldY = 24 * gp.tileSize;
        speed = 2;

        direction="down";


        solidArea = new Rectangle();
        solidArea.x = 5 * gp.scale;
        solidArea.y = 8 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 12 * gp.scale;
        solidArea.height = 15 * gp.scale;

    }

    public void getSlimeImage() {

        up1 = setup("SlimeUp");
        down1 = setup("SlimeDown");
        left1 = setup("SlimeLeft");
        right1 = setup("SlimeRight");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/enemies/slime/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hilfe");
        }
        return image;
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                    image = up1;
                break;
            case "down":
                    image = down1;
                break;
            case "left":
                    image = left1;
                break;
            case "right":
                    image = right1;
                break;
            default:
                image = down1; //falls was schiefgeht
                break;
        }

        g2.drawImage(image,(int)worldX,(int) worldY, null);
        //g2.setColor(Color.RED);    //Malt CollisionBox
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
