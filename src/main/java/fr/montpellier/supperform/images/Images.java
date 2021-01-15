package fr.montpellier.supperform.images;

import fr.montpellier.supperform.affichage.Identifiant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Images {



    public Images() {

        try {
            //FileInputStream fileInputStream = new FileInputStream("oui.txt");
            FileInputStream fileInputStreamIMG1 = new FileInputStream("src/main/resources/Image.jpg");

            BufferedImage imgBF1 = ImageIO.read(fileInputStreamIMG1);

            int[][] img1 = new int[imgBF1.getHeight()][imgBF1.getWidth()];
            //System.out.println(im.getRGB(100, 100));
            for (int i = 0; i < imgBF1.getHeight(); i++) {
                for (int j = 0; j < imgBF1.getWidth(); j++) {
                    img1[i][j] = imgBF1.getRGB(j, i);
                }
            }

            BufferedImage imgBF2 = ImageIO.read(fileInputStreamIMG1);

            int[][] img2 = new int[imgBF2.getHeight()][imgBF2.getWidth()];
            //System.out.println(im.getRGB(100, 100));
            for (int i = 0; i < imgBF2.getHeight(); i++) {
                for (int j = 0; j < imgBF2.getWidth(); j++) {
                    img2[i][j] = imgBF2.getRGB(j, i);
                }
            }

            /*for (int i = 0; i < img.length; i++) {
                System.out.println(Arrays.toString(img[i]) + "\n");
            }

            System.out.println(im.getHeight());
            System.out.println(im.getWidth());

            System.out.println(img.length);*/

            int[][] imgCompare = new int[imgBF2.getHeight()][imgBF2.getWidth()];
            for (int i = 0; i < imgBF2.getHeight(); i++) {
                for (int j = 0; j < imgBF2.getWidth(); j++) {
                    imgCompare[i][j] = imgBF2.getRGB(j, i);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }











}
