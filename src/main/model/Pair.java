package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pair {
    private String word;
    private BufferedImage img;

    public Pair(String word, String filePath) {
        this.word = word;
        File file = new File(filePath);
        try {
            this.img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("the file could not be loaded");
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public String getWord() {
        return word;
    }

/*    //@Override
    public void paint() {
        Graphics g = img.getGraphics();
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, 0, 0, null);
    }*/


}
