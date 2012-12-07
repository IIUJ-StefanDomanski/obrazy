/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obrazy;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    /*
     * OLD
    public static void main(String[] args) {
        Picture pic = new Picture("LENA_512.jpg"), wir = new Picture("WIR_360.jpg"),
                kob = new Picture("kobieta.jpg");
        pic.show();
        pic.sepia(40, 50).show();
        pic.rotate(30).show();
        pic.rotate(45).show();
        pic.rotate(180).show();
        wir.light_levels(2).show();
        pic.duplicate(3).show();
        pic.plus(new Picture(516, 516, new Color(100, 100, 100))).show();
        pic.minus(new Picture(516, 516, new Color(100, 100, 100))).show();
        pic.times(new Picture(516, 516, new Color(5, 5, 5))).show();
        pic.divide(new Picture(516, 516, new Color(5, 5, 5))).show();
        pic.sqrt().show();
        pic.power().show();
        Picture.RGBtoHSV(pic)[2].show();
        Histogram hist = new Histogram(Picture.RGBtoHSV(pic)[2]);
        hist.expand().show();
        hist.flatten().show();
        pic = new Picture("kobieta.jpg");
        pic.show();
        hist = new Histogram(Picture.RGBtoHSV(pic)[2]);
        Picture.RGBtoHSV(pic)[2].show();
        hist.flatten().show();

        Histogram sciem = new Histogram(Picture.RGBtoHSV(pic)[2]);
        sciem.contrast(0.7).show();
        Histogram wyr = new Histogram(Picture.RGBtoHSV(pic)[2]);
        wyr.flatten().show();
        Histogram szer = new Histogram(Picture.RGBtoHSV(pic)[2]);
        szer.expand().show();
        Histogram hKC = new Histogram(kob);
        hKC.flatten().show();
        Histogram hKB = new Histogram(Picture.RGBtoHSV(kob)[2]);
        hKB.expand().show();
        try {
            pic.writeToFileRGB("lena.txt");
            Picture.RGBtoHSV(pic)[2].writeToFileRGB("lena_B.txt");
            sciem.contrast(0.7).writeToFileRGB("lena_B_C.txt");
            wyr.flatten().writeToFileRGB("lena_B_F.txt");
            szer.expand().writeToFileRGB("lena_B_E.txt");
            kob.writeToFileRGB("kobieta.txt");
            Picture.RGBtoHSV(kob)[2].writeToFileRGB("kobieta_B.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        pic.sepia(40, 50).show();
    }
    */
    /*
    public static void main(String[] args) {
        Picture jezioro = new Picture("lake.jpg"), orzel = new Picture("eagle.jpg"),
                tex = new Picture("8.jpg"), kula = new Picture("kola.jpg"),
                obr1_0 = new Picture("s1.png"), obr1_1 = new Picture("s2.png"),
                obr2_0 = new Picture("krol1.png"), obr2_1 = new Picture("krol2.png"),
                lena = new Picture("LENA_512.jpg"), ok = new Picture("ok.jpg");
//
//        lena.plus(new Picture(516, 516, new Color(100, 100, 100))).show();
//        Z4.add(jezioro, orzel).show();
//        Z4.addWithScale(jezioro, orzel, 0.5).show();
//        Z4.addWithMod(jezioro, orzel).show();
//        Z4.addWithSat(kula, tex, 0.8).show();
//        Z4.sub(jezioro, orzel).show();
//        Z4.subWithScale(jezioro, orzel, 0.5).show();
//        Z4.subWithMod(jezioro, orzel).show();
//        Z4.sub(obr1_0, obr1_1).show();
//        Z4.sub(obr2_0, obr2_1).show();
//
//        jezioro.times(orzel).show();
//        Z4.mult(lena, ok).show();
//        Z4.mult(ok, lena).show();
        jezioro.divide(orzel).show();
        orzel.divide(jezioro).show();
        obr1_0.divide(obr1_1).show();
        obr2_1.divide(obr2_0).show();
        obr2_0.divide(obr2_1.divide(obr2_0)).show();
        Z4.mult(obr1_0.divide(obr1_1).minus(new Picture(obr1_0.width(), obr1_0.height(), new Color(1, 1, 1))),
               new Picture(obr1_0.width(), obr1_0.height(), new Color(150, 0, 0))).show();
        obr1_0.divide2(obr1_1).show();
    }
     *
     */
    
//    public static void main(String[] args){
//        Picture lena = new Picture("LENA_512.jpg"),
//                lenaB = Picture.RGBtoHSV(lena)[2];
//
////        Z5.noise(lena, 0.6, 150).show();
////        Z5.noisePiS(lena, 0.4).show();
////        Z5.noiseNorm(lena, 0.4, 40, 0.4).show();
//
//        Picture pic1 = Z5.noise(lenaB, 0.6, 150), pic2 =
//        Z5.noisePiS(lenaB, 0.4), pic3 =
//        Z5.noiseNorm(lenaB, 0.4, 40, 0.4);
//
//        pic1.show();
//        pic2.show();
//        pic3.show();
//
////        Z5.avgFilter(pic1, 1).show();
////        Z5.avgFilter(pic1, 2).show();
////        Z5.avgFilter(pic1, 3).show();
////        Z5.avgFilter(pic2, 1).show();
////        Z5.avgFilter(pic3, 1).show();
//
//
////        Z5.medFilter(pic1, 1).show();
////        Z5.medFilter(pic2, 1).show();
////        Z5.medFilter(pic3, 1).show();
//
//        Z5.gaussFilter(pic1).show();
//        Z5.gaussFilter(pic2).show();
//        Z5.gaussFilter(pic3).show();
//    }

    public static void main(String[] args){
        Picture lena = new Picture("LENA_512.jpg"),
                fig = new Picture("figury.png"),
                se1a = new Picture(3,3),
                se1b = new Picture(5,5),
                se2a = new Picture(3,3),
                se7a = new Picture(3,3),
                se7b = new Picture(3,3),
                se8a = new Picture(3,3),
                se8b = new Picture(3,3);

        Picture erofig, erolena;
        System.out.println(Color.gray.getRed());

        se1a.set(0, 0, Color.black);
        se1a.set(0, 1, Color.red);
        se1a.set(0, 2, Color.black);
        se1a.set(1, 0, Color.red);
        se1a.set(1, 1, Color.red);
        se1a.set(1, 2, Color.red);
        se1a.set(2, 0, Color.black);
        se1a.set(2, 1, Color.red);
        se1a.set(2, 2, Color.black);


        se1b.set(0, 0, Color.black);
        se1b.set(0, 1, Color.black);
        se1b.set(0, 2, Color.red);
        se1b.set(0, 3, Color.black);
        se1b.set(0, 4, Color.black);

        se1b.set(1, 0, Color.black);
        se1b.set(1, 1, Color.red);
        se1b.set(1, 2, Color.red);
        se1b.set(1, 3, Color.red);
        se1b.set(1, 4, Color.black);

        se1b.set(2, 0, Color.red);
        se1b.set(2, 1, Color.red);
        se1b.set(2, 2, Color.red);
        se1b.set(2, 3, Color.red);
        se1b.set(2, 4, Color.red);

        se1b.set(3, 0, Color.black);
        se1b.set(3, 1, Color.red);
        se1b.set(3, 2, Color.red);
        se1b.set(3, 3, Color.red);
        se1b.set(3, 4, Color.black);
        
        se1b.set(4, 0, Color.black);
        se1b.set(4, 1, Color.black);
        se1b.set(4, 2, Color.red);
        se1b.set(4, 3, Color.black);
        se1b.set(4, 4, Color.black);

        se2a.set(0, 0, Color.red);
        se2a.set(0, 1, Color.red);
        se2a.set(0, 2, Color.red);
        se2a.set(1, 0, Color.red);
        se2a.set(1, 1, Color.red);
        se2a.set(1, 2, Color.red);
        se2a.set(2, 0, Color.red);
        se2a.set(2, 1, Color.red);
        se2a.set(2, 2, Color.red);

        se7a.set(0, 0, Color.gray);
        se7a.set(0, 1, Color.gray);
        se7a.set(0, 2, Color.black);
        se7a.set(1, 0, Color.gray);
        se7a.set(1, 1, Color.red);
        se7a.set(1, 2, Color.red);
        se7a.set(2, 0, Color.black);
        se7a.set(2, 1, Color.red);
        se7a.set(2, 2, Color.black);

        se7b.set(0, 0, Color.red);
        se7b.set(0, 1, Color.gray);
        se7b.set(0, 2, Color.black);
        se7b.set(1, 0, Color.gray);
        se7b.set(1, 1, Color.red);
        se7b.set(1, 2, Color.gray);
        se7b.set(2, 0, Color.black);
        se7b.set(2, 1, Color.gray);
        se7b.set(2, 2, Color.red);

        se8a.set(0, 0, Color.red);
        se8a.set(0, 1, Color.red);
        se8a.set(0, 2, Color.red);
        se8a.set(1, 0, Color.red);
        se8a.set(1, 1, Color.gray);
        se8a.set(1, 2, Color.red);
        se8a.set(2, 0, Color.red);
        se8a.set(2, 1, Color.red);
        se8a.set(2, 2, Color.red);

        se8b.set(0, 0, Color.gray);
        se8b.set(0, 1, Color.red);
        se8b.set(0, 2, Color.red);
        se8b.set(1, 0, Color.red);
        se8b.set(1, 1, Color.red);
        se8b.set(1, 2, Color.red);
        se8b.set(2, 0, Color.red);
        se8b.set(2, 1, Color.red);
        se8b.set(2, 2, Color.red);

        fig = fig.level(125);
        lena = Picture.RGBtoHSV(lena)[2].level(125);
//        erofig = Z6.erosion(fig, se1a, Color.white);
//        Z4.sub(erofig, fig).show();
//
//        erofig = Z6.erosion(fig, se1b, Color.white);
//        Z4.sub(erofig, fig).show();
//
//        erolena = Z6.erosion(lena, se1a, Color.white);
//        Z4.sub(erolena, lena).show();
//
//        erolena = Z6.erosion(lena, se2a, Color.white);
//        Z4.sub(erolena, lena).show();


//        erofig = Z6.erosion(fig, se1a, Color.black);
//        erofig.show();
//        Z4.sub(erofig, fig).show();
//
//        erofig = Z6.erosion(fig, se1b, Color.black);
//        erofig.show();
//        Z4.sub(erofig, fig).show();
//
//        erolena = Z6.erosion(lena, se1a, Color.black);
//        erolena.show();
//        Z4.sub(erolena, lena).show();
//
//        erolena = Z6.erosion(lena, se2a, Color.black);
//        erolena.show();
//        Z4.sub(erolena, lena).show();


//        erofig = Z6.erosion(Z6.erosion(fig, se1a, Color.white), se1a, Color.black);
//        Z4.sub(erofig, fig).show();
//        //Z4.sub(fig, erofig).show();
//        erofig = Z6.erosion(Z6.erosion(fig, se1a, Color.black), se1a, Color.white);
//        //Z4.sub(erofig, fig).show();
//        Z4.sub(fig, erofig).show();

//          Z6.hitOrMiss(new Picture("figury.png").level(125), se7a, Color.white, Color.black).show();
//          Z6.hitOrMiss(new Picture("figury.png").level(125), se7b, Color.white, Color.black).show();

//          Z6.shade(new Picture("figury.png").level(125), se8a, Color.white, Color.black).show();
//          Z6.shade(new Picture("figury.png").level(125), se8b, Color.white, Color.black).show();
//
//          Z6.shade(new Picture("figury.png").level(125), se7a, Color.white, Color.black).show();
//          Z6.shade(new Picture("figury.png").level(125), se7b, Color.white, Color.black).show();

          Picture lenaRGB = new Picture("LENA_512.jpg");

          Z6.erosionRGB(lenaRGB, se1a).show();
          Z6.erosionRGB(lenaRGB, se2a).show();

          Z6.dylanRGB(lenaRGB, se1a).show();
          Z6.dylanRGB(lenaRGB, se2a).show();
    }
}
