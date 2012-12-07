/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package obrazy;

import java.awt.Color;
import java.io.*;

/**
 *
 * @author Stefan
 */
public class Histogram {
    Picture pic;
    private int[] hR, hG, hB;

    public Histogram(Picture pic){
        hR = new int[256];
        hG = new int[256];
        hB = new int[256];
        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                Color c = pic.get(i, j);
                hR[c.getRed()]++;
                hG[c.getGreen()]++;
                hB[c.getBlue()]++;
            }
        }
        this.pic=pic;
    }

    public void writeToFile(String path) throws FileNotFoundException {
        File f = new File(path);
        PrintWriter zapis = new PrintWriter(path);
        for (int i = 0; i < 256; i++) {
            zapis.println(hR[i] + " " + hG[i] + " " + hB[i]);
        }
        zapis.close();
    }

    public Picture expand(){
        Picture out = new Picture(pic.width(), pic.height());
        int vmxR=0, vmxG=0, vmxB=0,
            vmnR=0, vmnG=0, vmnB=0;
        int[] lutR = new int[256],
              lutG = new int[256],
              lutB = new int[256];

        for(int i=0; i<256; i++)
            if(hR[i]>5){
                vmnR = i;
                break;
            }
        for(int i=0; i<256; i++)
            if(hG[i]>5){
                vmnG = i;
                break;
            }
        for(int i=0; i<256; i++)
            if(hB[i]>5){
                vmnB = i;
                break;
            }
        for(int i=255; i>-1; i--)
            if(hR[i]>5){
                vmxR = i;
                break;
            }
        for(int i=255; i>-1; i--)
            if(hG[i]>5){
                vmxG = i;
                break;
            }
        for(int i=255; i>-1; i--)
            if(hB[i]>5){
                vmxB = i;
                break;
            }

        for(int i=0; i<256; i++){
            lutR[i]=(int)(256f/(vmxR-vmnR)*(i-vmnR));
            if(lutR[i]<0)lutR[i]=0;
            if(lutR[i]>255)lutR[i]=255;
            lutG[i]=(int)(256f/(vmxG-vmnG)*(i-vmnG));
            if(lutG[i]<0)lutG[i]=0;
            if(lutG[i]>255)lutG[i]=255;
            lutB[i]=(int)(256f/(vmxB-vmnB)*(i-vmnB));
            if(lutB[i]<0)lutB[i]=0;
            if(lutB[i]>255)lutB[i]=255;
        }

        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                Color c = pic.get(i, j);
                out.set(i, j, new Color(lutR[c.getRed()], lutG[c.getGreen()], lutB[c.getBlue()]));
            }
        }
        return out;
    }

    public Picture flatten(){
        Picture out = new Picture(pic.width(), pic.height());
        int[] lutR = new int[256],
              lutG = new int[256],
              lutB = new int[256];
        double[] dR = new double[256],
                   dG = new double[256],
                   dB = new double[256];
        double d0R=-1, d0G=-1, d0B=-1;

        for(int i=0; i<256; i++){
            for(int j=0; j<=i; j++){
                dR[i]+=hR[j];
                dG[i]+=hG[j];
                dB[i]+=hB[j];
            }
            dR[i]/=(pic.width()*pic.height());
            if(d0R==-1 && dR[i]>0)
                d0R=dR[i];
            dG[i]/=(pic.width()*pic.height());
            if(d0G==-1 && dG[i]>0)
                d0G=dG[i];
            dB[i]/=(pic.width()*pic.height());
            if(d0B==-1 && dB[i]>0)
                d0B=dB[i];
        }

        for(int i=0; i<256; i++){
            lutR[i]=(int) ((dR[i] - d0R)/(1-d0R) * 255);
            lutG[i]=(int) ((dG[i] - d0G)/(1-d0G) * 255);
            lutB[i]=(int) ((dB[i] - d0B)/(1-d0B) * 255);
        }

        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                Color c = pic.get(i, j);
                out.set(i, j, new Color(lutR[c.getRed()], lutG[c.getGreen()], lutB[c.getBlue()]));
            }
        }
        return out;
    }

    private int getColor(Color c, int i){
        switch(i){
            case 0: return c.getRed();
            case 1: return c.getGreen();
            case 2: return c.getBlue();
            default: return -1;
        }
    }

    public Picture contrast(double a){
        Picture out = new Picture(pic.width(), pic.height());
        int[] lut = new int[256];


        for(int i=0; i<256; i++){
            double w = a*(i-127.5)+127.5;
            if(w<0){
                lut[i]=0;
            }else if(w>255){
                lut[i]=255;
            }else{
                lut[i]=(int) w;
            }
        }

        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                Color c = pic.get(i, j);
                out.set(i, j, new Color(lut[c.getRed()], lut[c.getGreen()], lut[c.getBlue()]));
            }
        }
        return out;
    }
}
