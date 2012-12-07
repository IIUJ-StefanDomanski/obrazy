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
}
