package fr.montpellier.supperform.images;

import fr.montpellier.supperform.affichage.Identifiant;
import org.graalvm.compiler.api.replacements.Snippet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

    /**
     * Les étapes sont :
     * 1 - recupérer l'image à comparer
     * 2 - redimensionner l'image de Base par rapport à celle à comparer
     * 3 - trouver les carrées noir par rapport à l'image de base
     * 4 - faire la notation
     *
     * faire un carré de 45 de coté
     *  coté droit faire au min 2395 sur la largeur
     *  en bas faire au min 3430 sur la hauteur
     *  en haut faire au min 30 sur la hauteur
     */



    public Images() {

        Color[][] couleurDeBase = creerNouvelleImage(imgBuf);
        Color[][] couleurCompare = new Color[0][];
        
        try {   
            couleurCompare = creerNouvelleImage(ImageIO.read(new FileInputStream("src/main/resources/Image3.jpg")));
            //couleurCompare = creerNouvelleImage(ImageIO.read(new FileInputStream("Test2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        genererImage(couleurDeBase, couleurCompare, "Test2");
        //genererImage(couleurDeBase, couleurCompare, "ImageSansCarréBas");


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

        int test = 0;

        for (int i = /*(imgBuf.getHeight() * 95) / 100*/0; i < imgBuf.getHeight(); i++) {
            for (int j = 0; j < imgBuf.getWidth(); j++) {

               /* int a = couleurBase[i][j].getAlpha();
                int r = couleurBase[i][j].getRed();
                int g = couleurBase[i][j].getGreen();
                int b = couleurBase[i][j].getBlue();*/

               /* int a = Math.min(couleurBase[i][j].getAlpha(), couleurCompare[i][j].getAlpha());
                int r = Math.min(couleurBase[i][j].getRed(), couleurCompare[i][j].getRed());
                int g = Math.min(couleurBase[i][j].getGreen(), couleurCompare[i][j].getGreen());
                int b = Math.min(couleurBase[i][j].getBlue(), couleurCompare[i][j].getBlue());*/

                int a = Math.max(couleurBase[i][j].getAlpha(), couleurCompare[i][j].getAlpha());
                int r = Math.max(couleurBase[i][j].getRed(), couleurCompare[i][j].getRed());
                int g = Math.max(couleurBase[i][j].getGreen(), couleurCompare[i][j].getGreen());
                int b = Math.max(couleurBase[i][j].getBlue(), couleurCompare[i][j].getBlue());

                /*if(couleurBase[i][j].getRed() < 10 && couleurBase[i][j].getGreen() < 10 & couleurBase[i][j].getBlue() < 10){
                    System.out.println(i + "-" + j);
                }*/


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

    public BufferedImage redimensionnerImage(BufferedImage bufferedImage, int x, int y){

        // crée l'image de sortie
        BufferedImage img = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        // balancer l'image d'entrée à l'image de sortie
        Graphics2D g = img.createGraphics();
        g.drawImage(imgBuf, x, y, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
        g.dispose();

        return img;
    }

    public void trouverCarre(Color[][] couleur){

        int compteur = 0, min = 0, max = 0;

        for (int i =  0; i < (couleur[0].length; i++) {
            for (int j = 0; j < couleur.length; j++) {

                if(couleur[i][j].getRed() < 50 && couleur[i][j].getGreen() < 50 && couleur[i][j].getBlue() < 50){
                    compteur ++;
                    if(min == 0){
                        min = j;
                    }
                    max = j;

                }

            }

            if(compteur > 30){
                break;
            }

        }


    }

    public void decalageHorizontal(int decalage, Color [][] couleur, int minX, int maxX, int minY, int maxY){
        for (int i =  minY; i < maxY; i++) {
            for (int j = minX; j < maxX; j++) {

            }
        }
    }

    public int colorierImage(int alpha, int red, int green, int blue){
        return (alpha <<24) | (red <<16) | (green <<8) | blue;
    }

}
