package fr.montpellier.supperform.images;

import fr.montpellier.supperform.affichage.Identifiant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Images {



    public Images() {

        try {

            BufferedImage imgBF1 = ImageIO.read(new FileInputStream("src/main/resources/Image.jpg"));

            int[][] img1 = new int[imgBF1.getHeight()][imgBF1.getWidth()];

            for (int i = 0; i < imgBF1.getHeight(); i++) {
                for (int j = 0; j < imgBF1.getWidth(); j++) {
                    img1[i][j] = imgBF1.getRGB(j, i);
                }
            }

            BufferedImage imgBF2 = ImageIO.read(new FileInputStream("src/main/resources/Image3.jpg"));

            int[][] img2 = new int[imgBF2.getHeight()][imgBF2.getWidth()];
            Color[][] couleur = new Color[imgBF2.getHeight()][imgBF2.getWidth()];

            for (int i = 0; i < imgBF2.getHeight(); i++) {
                for (int j = 0; j < imgBF2.getWidth(); j++) {
                    img2[i][j] = imgBF2.getRGB(j, i);
                    couleur[i][j] = new Color(imgBF2.getRGB(j, i));
                }
            }

            /*for (int[] ints : img1) {
                System.out.println(Arrays.toString(ints) + "\n");
            }*/

            for (Color[] colors : couleur) {
                System.out.println(Arrays.toString(colors) + "\n");
            }

            System.out.println(imgBF1.getHeight());
            System.out.println(imgBF1.getWidth());

            System.out.println(img1.length);
            System.out.println(img1[0].length);

            int[][] img3 = new int[imgBF2.getHeight()][imgBF2.getWidth()];

            for (int i = 0; i < imgBF2.getHeight(); i++) {
                for (int j = 0; j < imgBF2.getWidth(); j++) {
                    img3[i][j] = (imgBF2.getRGB(j, i) + imgBF1.getRGB(j, i)) / 2;
                }
            }

            /*for (int[] ints : img3) {
                System.out.println(Arrays.toString(ints) + "\n");
            }

            System.out.println(img1.length);
            System.out.println(img1[0].length);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }











}
