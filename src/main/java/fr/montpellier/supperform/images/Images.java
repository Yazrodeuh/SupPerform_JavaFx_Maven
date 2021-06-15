package fr.montpellier.supperform.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Images {

    private BufferedImage imgBuf;
    private Color[][] couleurImg;
    //private double quota = 99.9/100.0;

    private int heightCarre, widthCarre, heightInterieurCarre, widthInterieurCarre;
    private List<int[]> pixelDejaVu;
    private List<int[]> coordCarre;


    {
        try {
            //imgBuf = ImageIO.read(new FileInputStream("src/main/resources/Image.jpg"));
            //imgBuf = ImageIO.read(new FileInputStream("test-parfait.png"));
            imgBuf = ImageIO.read(new FileInputStream("imgTest/tif200-normal++-n_b.tif"));

            couleurImg = creerNouvelleImage(imgBuf);

            //System.out.println(Arrays.deepToString(couleurImg));

            widthCarre = (int)Math.round((1.3521 * imgBuf.getWidth()) / 100);
            heightCarre = (int)Math.round((1 * imgBuf.getHeight()) / 100);

            System.out.println(widthCarre);
            System.out.println(heightCarre);

            pixelDejaVu = new LinkedList<>();
            coordCarre = new LinkedList<>();

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

        //genererImage(couleurImg, "testv2-jpg");
        carreQCM();

        System.out.println(verifVerticaleCarre(1743, 502, new int[]{1743, 502}));
        System.out.println(verifHorizontaleCarre(1743, 502, new int[]{1743, 502}));

        int test = 0;

        for (int[] lists : coordCarre) {

            System.out.println(Arrays.toString(lists));
            test++;
        }

        System.out.println(test);

        System.out.println("finit");

    }


    private Color[][] creerNouvelleImage(BufferedImage bufferedImage){

        Color[][] colors = new Color[bufferedImage.getHeight()][bufferedImage.getWidth()];

        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                if(estPixelNoir(new Color(bufferedImage.getRGB(j, i)))) {
                    //System.out.println("ici");
                    colors[i][j] = new Color(0, 0, 0);
                }else {
                    colors[i][j] = new Color(255, 255, 255);
                }
            }
        }
        return colors;
    }

    private void carreQCM(){
        int count = 0;
        //System.out.println(couleurImg.length);
        for (int i = 0; i < couleurImg.length - heightCarre; i++) {
            for (int j = 0; j < couleurImg[i].length - widthCarre; j++) {
                if((pixelDejaVu.size() == 0 || !pixelPresent(new int[]{i, j})) && estPixelNoir(couleurImg[i][j])){
                    count++;
                    //System.out.println("ici " + count);

                    System.out.println(" ");
                    System.out.println(i + " - " + j);

                    //System.out.println(pixelDejaVu.size());

                    if(verifVerticaleCarre(j, i, new int[]{j, i}) && verifHorizontaleCarre(j, i, new int[]{j, i})){
                        ajoutDejaVu(i, j);
                        //System.out.println("ici");
                        coordCarre.add(new int[]{i, j});
                        //System.out.println(i + " - " + j);
                        if(j + widthCarre < couleurImg[i].length){
                            j+= widthCarre;
                        }else if(i +heightCarre < couleurImg.length){
                            i+=heightCarre;
                        }
                    }
                }

                //System.out.println(i + "-" + j);
            }

        }
        //System.out.println(pixelDejaVu.size());
    }

    private boolean verifVerticaleCarre(int x, int y, int[] positionDepart){

        //System.out.println(couleurImg.length);
        System.out.println("y - verti : " + y);
        System.out.println("x - verti : " + x);
        System.out.println(Arrays.toString(positionDepart));

        if(y == positionDepart[1] + heightCarre && x == positionDepart[0] + 1){
            return true;
        }

        if(y == positionDepart[1] + heightCarre && x < positionDepart[0] + 1){
            y = positionDepart[1];
            x++;

            System.out.println("y - verti change : " + y);
            System.out.println("x - verti chnage : " + x);

        }

        if(!estPixelNoir(couleurImg[y][x]) || !estPixelNoir(couleurImg[y][x + widthCarre])){
            return false;
        }
        return verifVerticaleCarre(x, y+1, positionDepart);

    }

    private boolean verifHorizontaleCarre(int x, int y, int[] positionDepart){ //TODO pb ici

        //System.out.println("y - hori : " + y);
        //System.out.println("x - hori : " + x);

        if(x == positionDepart[0] + widthCarre && y == positionDepart[1] + 1){
            return true;
        }

        if(x == positionDepart[0] + widthCarre && y != positionDepart[1] + 1){
            x = positionDepart[0];
            y++;
        }

        if(!estPixelNoir(couleurImg[y][x]) || !estPixelNoir(couleurImg[y + heightCarre][x])){
            return false;
        }
        return verifHorizontaleCarre(x+1, y, positionDepart);
    }

    private boolean verifReponseCoche(){

        return false;
    }

    private boolean estPixelNoir(Color color){
        return color.getBlue() + color.getRed() + color.getGreen() < 150;
    }

    private void ajoutDejaVu(int x, int y){

        for (int i = 0; i < Math.sqrt(heightCarre); i++) {
            for (int j = 0; j < Math.sqrt(widthCarre); j++) {
                pixelDejaVu.add(new int[]{y+i, x+j});
            }
        }
        System.out.println(pixelDejaVu.size());

    }

    private boolean pixelPresent(int[] coord){
        return pixelDejaVu.contains(coord);
    }







    private void genererImage(Color[][] couleur, String name){
        
        File f = null;
        BufferedImage bufferedImage = new BufferedImage(imgBuf.getWidth(), imgBuf.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int i = /*(imgBuf.getHeight() * 95) / 100*/0; i < imgBuf.getHeight(); i++) {
            for (int j = 0; j < imgBuf.getWidth(); j++) {

               int a = couleur[i][j].getAlpha();

                //System.out.println(a);

               if(couleur[i][j].getRed() + couleur[i][j].getGreen() + couleur[i][j].getBlue() < 400){
                   int b = 0;
                   int p = (a << 24) | (0) | b;
                   bufferedImage.setRGB(j, i, p);
               }else {
                   int r = 255;
                   int g = 255;
                   int b = 255;

                   int p = (a<<24) | (r<<16) | (g<<8) | b;
                   bufferedImage.setRGB(j, i, p);
               }

                /*if(couleurBase[i][j].getRed() < 10 && couleurBase[i][j].getGreen() < 10 & couleurBase[i][j].getBlue() < 10){
                    System.out.println(i + "-" + j);
                }*/


            }
            //System.out.println("");
           // System.out.println(i);
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

        for (int i =  0; i < couleur[0].length; i++) {
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
