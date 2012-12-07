/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package obrazy;

import java.awt.Color;

/**
 *
 * @author Stefan
 */
public class Z6 {
    public static Picture erosion(Picture pic, Picture sePic, Color bg){
        int bgR=bg.getRed(), bgG=bg.getGreen(), bgB=bg.getBlue();
        boolean[][] se = new boolean[sePic.width()][sePic.height()];
        Picture out = new Picture(pic.width(), pic.height());

        for(int i=0; i<sePic.width(); i++)
            for(int j=0; j<sePic.height(); j++)
                if(sePic.get(i, j).getRed()>0)
                    se[i][j]=true;

        int mid = sePic.width()/2;
        boolean flag;

        for(int i=0; i<pic.width(); i++)
            for(int j=0; j<pic.height(); j++){
                flag = true;
                for(int k=i-mid; k<=i+mid&&flag; k++)
                    for(int l=j-mid; l<=j+mid&&flag; l++)
                        if(k>-1&&l>-1&&k<pic.width()&&l<pic.height())
                            if(se[k-i+mid][l-j+mid]&&pic.get(k, l).getBlue()==bgB&&pic.get(k, l).getRed()==bgR&&pic.get(k, l).getGreen()==bgG){
                                out.set(i, j, new Color(bgR,bgG,bgB));
                                flag = false;
                            }
                if(flag)out.set(i, j, pic.get(i, j));
            }

        return out;
    }

    public static Picture erosionRGB(Picture pic, Picture sePic){
        boolean[][] se = new boolean[sePic.width()][sePic.height()];
        Picture out = new Picture(pic.width(), pic.height());

        for(int i=0; i<sePic.width(); i++)
            for(int j=0; j<sePic.height(); j++)
                if(sePic.get(i, j).getRed()>0)
                    se[i][j]=true;

        int mid = sePic.width()/2, min;
        Color c;

        for(int i=0; i<pic.width(); i++)
            for(int j=0; j<pic.height(); j++){
                min = 1000;
                c = pic.get(i, j);
                for(int k=i-mid; k<=i+mid; k++)
                    for(int l=j-mid; l<=j+mid; l++)
                        if(k>-1&&l>-1&&k<pic.width()&&l<pic.height()&&se[k-i+mid][l-j+mid]){
                            Color c1 = pic.get(k, l);
                            if(c1.getRed()+c1.getBlue()+c1.getGreen()<min){
                                c = c1;
                                min = c1.getRed()+c1.getBlue()+c1.getGreen();
                            }
                        }
                out.set(i, j, c);
            }

        return out;
    }

    public static Picture dylanRGB(Picture pic, Picture sePic){
        boolean[][] se = new boolean[sePic.width()][sePic.height()];
        Picture out = new Picture(pic.width(), pic.height());

        for(int i=0; i<sePic.width(); i++)
            for(int j=0; j<sePic.height(); j++)
                if(sePic.get(i, j).getRed()>0)
                    se[i][j]=true;

        int mid = sePic.width()/2, max;
        Color c;

        for(int i=0; i<pic.width(); i++)
            for(int j=0; j<pic.height(); j++){
                max = -1;
                c = pic.get(i, j);
                for(int k=i-mid; k<=i+mid; k++)
                    for(int l=j-mid; l<=j+mid; l++)
                        if(k>-1&&l>-1&&k<pic.width()&&l<pic.height()&&se[k-i+mid][l-j+mid]){
                            Color c1 = pic.get(k, l);
                            if(c1.getRed()+c1.getBlue()+c1.getGreen()>max){
                                c = c1;
                                max = c1.getRed()+c1.getBlue()+c1.getGreen();
                            }
                        }
                out.set(i, j, c);
            }

        return out;
    }

    public static Picture hitOrMiss(Picture pic, Picture sePic, Color c0, Color c1){
        int c0R=c0.getRed(), c0G=c0.getGreen(), c0B=c0.getBlue();
        int c1R=c1.getRed(), c1G=c1.getGreen(), c1B=c1.getBlue();
        int[][] se = new int[sePic.width()][sePic.height()];

        for(int i=0; i<sePic.width(); i++)
            for(int j=0; j<sePic.height(); j++)
                if(sePic.get(i, j).getRed() == 128)
                    se[i][j]=2;
                else if(sePic.get(i, j).getRed() > 0)
                    se[i][j]=1;
                else
                    se[i][j]=0;

        int mid = sePic.width()/2;
        boolean flag;

        for(int i=0; i<pic.width(); i++)
            for(int j=0; j<pic.height(); j++){
                flag = true;
                for(int k=i-mid; k<=i+mid&&flag; k++)
                    for(int l=j-mid; l<=j+mid&&flag; l++)
                        if(k>-1&&l>-1&&k<pic.width()&&l<pic.height())
                            if(se[k-i+mid][l-j+mid]==0)
                                if(pic.get(k, l).getBlue() != c0B || pic.get(k, l).getRed() != c0R || pic.get(k, l).getGreen() != c0G)
                                {
                                    pic.set(i, j, Color.black);
                                    flag = false;
                                }
                            else if(se[k - i + mid][l - j + mid]==1)
                                if(pic.get(k, l).getBlue() != c1B || pic.get(k, l).getRed() != c1R || pic.get(k, l).getGreen() != c1G)
                                {
                                    pic.set(i, j, Color.black);
                                    flag = false;
                                }
                if(flag)pic.set(i, j, Color.white);
            }

        return pic;
    }

    public static Picture shade(Picture pic, Picture sePic, Color c0, Color c1){
        int c0R=c0.getRed(), c0G=c0.getGreen(), c0B=c0.getBlue();
        int c1R=c1.getRed(), c1G=c1.getGreen(), c1B=c1.getBlue();
        int[][] se = new int[sePic.width()][sePic.height()];

        for(int i=0; i<sePic.width(); i++)
            for(int j=0; j<sePic.height(); j++)
                if(sePic.get(i, j).getRed() == 128)
                    se[i][j]=2;
                else if(sePic.get(i, j).getRed() > 0)
                    se[i][j]=1;
                else
                    se[i][j]=0;

        int mid = sePic.width()/2;
        boolean flag;

        for(int i=0; i<pic.width(); i++)
            for(int j=0; j<pic.height(); j++){
                flag = true;
                for(int k=i-mid; k<=i+mid&&flag; k++)
                    for(int l=j-mid; l<=j+mid&&flag; l++)
                        if(k>-1&&l>-1&&k<pic.width()&&l<pic.height())
                            if(se[k-i+mid][l-j+mid]==0)
                                if(pic.get(k, l).getBlue() != c0B || pic.get(k, l).getRed() != c0R || pic.get(k, l).getGreen() != c0G)
                                    flag = false;
                            else if(se[k - i + mid][l - j + mid]==1)
                                if(pic.get(k, l).getBlue() != c1B || pic.get(k, l).getRed() != c1R || pic.get(k, l).getGreen() != c1G)
                                    flag = false;
                if(flag)pic.set(i, j, Color.white);
            }

        return pic;
    }
}
