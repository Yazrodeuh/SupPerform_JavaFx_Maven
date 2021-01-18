package fr.montpellier.supperform.images;

import fr.montpellier.supperform.affichage.Identifiant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Images {

    private BufferedImage imgBuf;

    {
        try {
            imgBuf = ImageIO.read(new FileInputStream("src/main/resources/Image.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Images() {

        Color[][] couleurDeBase = creerNouvelleImage(imgBuf);
        Color[][] couleurCompare = new Color[0][];
        
        try {   
            couleurCompare = creerNouvelleImage(ImageIO.read(new FileInputStream("src/main/resources/Image3.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        genererImage(couleurDeBase, couleurCompare, "Test2");


    }

    private Color[][] creerNouvelleImage(BufferedImage bufferedImage){

        Color[][] colors = new Color[bufferedImage.getHeight()][bufferedImage.getWidth()];

        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                colors[i][j] = new Color(bufferedImage.getRGB(j, i));
            }
        }
        return colors;
    }

    private void genererImage(Color[][] couleurBase, Color[][] couleurCompare, String name){
        
        File f = null;
        BufferedImage bufferedImage = new BufferedImage(imgBuf.getWidth(), imgBuf.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < imgBuf.getHeight(); i++) {
            for (int j = 0; j < imgBuf.getWidth(); j++) {

                int a = Math.min(couleurBase[i][j].getAlpha(), couleurCompare[i][j].getAlpha());
                int r = Math.min(couleurBase[i][j].getRed(), couleurCompare[i][j].getRed());
                int g = Math.min(couleurBase[i][j].getGreen(), couleurCompare[i][j].getGreen());
                int b = Math.min(couleurBase[i][j].getBlue(), couleurCompare[i][j].getBlue());

                int p = (a<<24) | (r<<16) | (g<<8) | b;

                bufferedImage.setRGB(j, i, p);
            }
        }

        f = new File(name + ".png");

        try {
            ImageIO.write(bufferedImage, "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
