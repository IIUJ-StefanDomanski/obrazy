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
public class Z4 {

    public static Picture addWithScale(Picture pic1, Picture pic2, double w1)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r, g, b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic2.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = (int) (w1 * c.getRed() + (1 - w1) * c1.getRed());
                g = (int) (w1 * c.getGreen() + (1 - w1) * c1.getGreen());
                b = (int) (w1 * c.getBlue() + (1 - w1) * c1.getBlue());
                out.set(i, j, new Color(r, g, b));
            }
        }
        return out;
    }

    public static Picture add(Picture pic1, Picture pic2)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r, g, b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic2.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = c.getRed()+ c1.getRed();
                g = c.getGreen()+ c1.getGreen();
                b = c.getBlue()+ c1.getBlue();
                if(r>255)r=255;
                if(g>255)g=255;
                if(b>255)b=255;
                out.set(i, j, new Color(r, g, b));
            }
        }
        return out;
    }

    public static Picture addWithMod(Picture pic1, Picture pic2)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r, g, b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic2.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = (c.getRed()+ c1.getRed())%256;
                g = (c.getGreen()+ c1.getGreen())%256;
                b = (c.getBlue()+ c1.getBlue())%256;
                out.set(i, j, new Color(r, g, b));
            }
        }
        return out;
    }

    public static Picture addWithSat(Picture pic1, Picture pic2, double w1)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r, g, b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic2.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = (int) (c.getRed() + (1 - w1) * c1.getRed());
                g = (int) (c.getGreen() + (1 - w1) * c1.getGreen());
                b = (int) (c.getBlue() + (1 - w1) * c1.getBlue());
                if(r>255)r=255;
                if(g>255)g=255;
                if(b>255)b=255;
                out.set(i, j, new Color(r, g, b));
            }
        }
        return out;
    }

    public static Picture subWithScale(Picture pic1, Picture pic2, double w1)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r, g, b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic2.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = (int) (w1 * c.getRed() - (1 - w1) * c1.getRed());
                g = (int) (w1 * c.getGreen() - (1 - w1) * c1.getGreen());
                b = (int) (w1 * c.getBlue() - (1 - w1) * c1.getBlue());
                if(r<0)r=0;
                if(g<0)g=0;
                if(b<0)b=0;
                out.set(i, j, new Color(r, g, b));
            }
        }
        return out;
    }

    public static Picture sub(Picture pic1, Picture pic2)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r, g, b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic2.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = c.getRed() - c1.getRed();
                g = c.getGreen() - c1.getGreen();
                b = c.getBlue() - c1.getBlue();
                if(r<0)r=0;
                if(g<0)g=0;
                if(b<0)b=0;
                out.set(i, j, new Color(r, g, b));
            }
        }
        return out;
    }

    public static Picture subWithMod(Picture pic1, Picture pic2)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r, g, b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic2.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = (c.getRed()- c1.getRed()+256)%256;
                g = (c.getGreen()- c1.getGreen()+256)%256;
                b = (c.getBlue()- c1.getBlue()+256)%256;
                out.set(i, j, new Color(r, g, b));
            }
        }
        return out;
    }

    public static Picture mult(Picture pic1, Picture pic2)
    {
        Picture out = new Picture(pic1.width(), pic2.height());
        int r,g,b;

        for(int i = 0; i < pic1.width(); i++){
            for(int j = 0; j < pic1.height(); j++){
                Color c = pic1.get(i, j);
                Color c1 = pic2.get(i, j);
                r = (int) (c.getRed() * (c1.getRed() / 255.0));
                g = (int) (c.getGreen() * (c1.getGreen() / 255.0));
                b = (int) (c.getBlue() * (c1.getBlue() / 255.0));
                if(r>255)r=255;
                if(g>255)g=255;
                if(b>255)b=255;
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }
}
