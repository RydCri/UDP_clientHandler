package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    public String path;
    public int width;
    public int height;

    public int[] pixels;

    public SpriteSheet(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        } catch (IOException e ) {
            e.printStackTrace();
        }
        if (image == null) {
            return;
        }

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();

        pixels = image.getRGB(0,0,width,height,null, 0, width);

        //set hex prefix
        //pixels set to colorcode sheet
        for (int i = 0; i < pixels.length;i++){
            pixels[i] = (pixels[i] & 0xff) / 64;
        }
    }
}