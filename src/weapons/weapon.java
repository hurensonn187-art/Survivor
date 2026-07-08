package weapons;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class weapon {

    public int defaultDamage;

    public BufferedImage idle, weapon1, weapon2, weapon3, weapon4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

}