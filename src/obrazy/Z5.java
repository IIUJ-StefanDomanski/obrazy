/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package obrazy;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Stefan
 */
public class Z5 {

    static public Picture noise(Picture pic, double prob, int level){
        Picture out = new Picture(pic.width(), pic.height());
        int r,g,b,offs;

        Math.random();
        Math.random();
        Math.random();
        
        for(int i=0; i<pic.width(); i++){
            for(int j=0; j<pic.height(); j++){
                if(Math.random()<prob){
                    offs = (int) Math.round((Math.random()-0.5)*level);
                    Color c = pic.get(i, j);
                    r = c.getRed()+ offs;
                    g = c.getGreen()+ offs;
                    b = c.getBlue()+ offs;

                    if(r>255)r=255;
                    if(g>255)g=255;
                    if(b>255)b=255;

                    if(r<0)r=0;
                    if(g<0)g=0;
                    if(b<0)b=0;

                    out.set(i, j, new Color(r,g,b));
                }else{
                    out.set(i, j, pic.get(i, j));
                }
            }
        }
        return out;
    }

    static public Picture noiseNorm(Picture pic, double s, double m, double prob){
        Picture out = new Picture(pic.width(), pic.height());
        int r,g,b,offs;
        Random rand = new Random();

        Math.random();
        Math.random();
        Math.random();

        for(int i=0; i<pic.width(); i++){
            for(int j=0; j<pic.height(); j++){
                if(Math.random()<prob){
                    offs = (int) Math.round(rand.nextGaussian() * s + m);
                    Color c = pic.get(i, j);
                    r = c.getRed()+ offs;
                    g = c.getGreen()+ offs;
                    b = c.getBlue()+ offs;

                    if(r>255)r=255;
                    if(g>255)g=255;
                    if(b>255)b=255;

                    if(r<0)r=0;
                    if(g<0)g=0;
                    if(b<0)b=0;

                    out.set(i, j, new Color(r,g,b));
                }else{
                    out.set(i, j, pic.get(i, j));
                }
            }
        }
        return out;
    }

    static public Picture noisePiS(Picture pic, double prob){
        Picture out = new Picture(pic.width(), pic.height());
        int r,g,b,offs;

        Math.random();
        Math.random();
        Math.random();

        for(int i=0; i<pic.width(); i++){
            for(int j=0; j<pic.height(); j++){
                if(Math.random()<prob){
                    offs = ((int) (Math.random()*2)) * 255;
                    r = offs;
                    g = offs;
                    b = offs;
                    out.set(i, j, new Color(r,g,b));
                }else{
                    out.set(i, j, pic.get(i, j));
                }
            }
        }
        return out;
    }

    static public Picture avgFilter(Picture pic, int size){
        Picture out = new Picture(pic.width(), pic.height());
        int avgR, avgG, avgB;
        final int div = (size*2+1)*(size*2+1);

        for(int i=size; i<pic.width()-size; i++){
            for(int j=size; j<pic.height()-size; j++){
                avgR = 0; avgG = 0; avgB = 0;
                for(int k=i-size; k<=i+size; k++){
                    for(int l=j-size; l<=j+size; l++){
                        avgR += pic.get(k, l).getRed();
                        avgG += pic.get(k, l).getGreen();
                        avgB += pic.get(k, l).getBlue();
                    }
                }
                out.set(i, j, new Color(avgR/div,avgG/div,avgB/div));
            }
        }
        return out;
    }

    static public Picture medFilter(Picture pic, int size){
        Picture out = new Picture(pic.width(), pic.height());
        int avgR, avgG, avgB;
        final int div = (size*2+1)*(size*2+1);
        ArrayList alR, alG, alB;

        for(int i=size; i<pic.width()-size; i++){
            for(int j=size; j<pic.height()-size; j++){
                alR = new ArrayList(); alG = new ArrayList(); alB = new ArrayList();
                for(int k=i-size; k<=i+size; k++){
                    for(int l=j-size; l<=j+size; l++){
                        alR.add(pic.get(k, l).getRed());
                        alG.add(pic.get(k, l).getGreen());
                        alB.add(pic.get(k, l).getBlue());
                    }
                }
                Collections.sort(alR);
                Collections.sort(alG);
                Collections.sort(alB);
                out.set(i, j, new Color(((Integer)alR.get(div/2)).intValue() ,
                        ((Integer)alR.get(div/2)).intValue(),
                        ((Integer)alR.get(div/2)).intValue()));
            }
        }
        return out;
    }

    static public Picture gaussFilter(Picture pic){
        Picture out = new Picture(pic.width(), pic.height());
        int avgR, avgG, avgB;
        int size = 1;

        int[][] matrix = {{1,2,1},{2,4,2},{1,2,1}};
        int div = 16;
        //int div = 0, mwidth = size*2+1;
        //int[][] matrix = new int[mwidth][mwidth];
        //int mult = 1;

//        for(int i=0; i<size*2+1; i++){
//            for(int j=0; j<=i; j++){
//                matrix[mwidth-j-1][mwidth-i+j-1] = mult;
//                matrix[mwidth-j-1][mwidth-i+j-1] = mult;
//            }
//            mult *= 2;
//        }

        for(int i=size; i<pic.width()-size; i++){
            for(int j=size; j<pic.height()-size; j++){
                avgR = 0; avgG = 0; avgB = 0;
                for(int k=0; k<3; k++){
                    for(int l=0; l<3; l++){
                        avgR += matrix[k][l]*pic.get(k-1+i, l-1+j).getRed();
                        avgG += matrix[k][l]*pic.get(k-1+i, l-1+j).getGreen();
                        avgB += matrix[k][l]*pic.get(k-1+1, l-1+j).getBlue();
                    }
                }
                out.set(i, j, new Color(avgR/div,avgG/div,avgB/div));
            }
        }
        return out;
    }
}
