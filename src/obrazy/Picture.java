/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obrazy;

/*************************************************************************
 *  Compilation:  javac Picture.java
 *  Execution:    java Picture imagename
 *
 *  Data type for manipulating individual pixels of an image. The original
 *  image can be read from a file in jpg, gif, or png format, or the
 *  user can create a blank image of a given size. Includes methods for
 *  displaying the image in a window on the screen or saving to a file.
 *
 *  % java Picture mandrill.jpg
 *
 *  Remarks
 *  -------
 *   - pixel (x, y) is column x and row y, where (0, 0) is upper left
 *
 *   - see also GrayPicture.java for a grayscale version
 *
 *************************************************************************/
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *  This class provides methods for manipulating individual pixels of
 *  an image. The original image can be read from a file in JPEG, GIF,
 *  or PNG format, or the user can create a blank image of a given size.
 *  This class includes methods for displaying the image in a window on
 *  the screen or saving to a file.
 *  <p>
 *  By default, pixel (x, y) is column x, row y, where (0, 0) is upper left.
 *  The method setOriginLowerLeft() change the origin to the lower left.
 *  <p>
 *  For additional documentation, see
 *  <a href="http://introcs.cs.princeton.edu/31datatype">Section 3.1</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i>
 *  by Robert Sedgewick and Kevin Wayne.
 */
public final class Picture implements ActionListener {

    private BufferedImage image;               // the rasterized image
    private JFrame frame;                      // on-screen view
    private String filename;                   // name of file
    private boolean isOriginUpperLeft = true;  // location of origin
    private final int width, height;           // width and height

    /**
     * Create a blank w-by-h picture, where each pixel is black.
     */
    public Picture(int w, int h) {
        width = w;
        height = h;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // set to TYPE_INT_ARGB to support transparency
        filename = w + "-by-" + h;
    }

    /**
     * Create a blank w-by-h picture, where each pixel is given color.
     */
    public Picture(int w, int h, Color c) {
        width = w;
        height = h;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                set(i, j, c);
            }
        }
        // set to TYPE_INT_ARGB to support transparency
        filename = w + "-by-" + h;
    }

    /**
     * Create a picture by reading in a .png, .gif, or .jpg from
     * the given filename or URL name.
     */
    public Picture(String filename) {
        this.filename = filename;
        try {
            // try to read from file in working directory
            File file = new File(filename);
            if (file.isFile()) {
                image = ImageIO.read(file);
            } // now try to read from file in same directory as this .class file
            else {
                URL url = getClass().getResource(filename);
                if (url == null) {
                    url = new URL(filename);
                }
                image = ImageIO.read(url);
            }
            width = image.getWidth(null);
            height = image.getHeight(null);
        } catch (IOException e) {
            // e.printStackTrace();
            throw new RuntimeException("Could not open file: " + filename);
        }
    }

    /**
     * Create a picture by reading in a .png, .gif, or .jpg from a File.
     */
    public Picture(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open file: " + file);
        }
        if (image == null) {
            throw new RuntimeException("Invalid image file: " + file);
        }
        width = image.getWidth(null);
        height = image.getHeight(null);
        filename = file.getName();
    }

    public Picture getRed() {
        Picture out = new Picture(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = this.get(i, j);
                Color n = new Color(c.getRed(), 0, 0);
                out.set(i, j, n);
            }
        }
        return out;
    }

    public Picture getGreen() {
        Picture out = new Picture(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = this.get(i, j);
                Color n = new Color(0, c.getGreen(), 0);
                out.set(i, j, n);
            }
        }
        return out;
    }

    public Picture getBlue() {
        Picture out = new Picture(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = this.get(i, j);
                Color n = new Color(0, 0, c.getBlue());
                out.set(i, j, n);
            }
        }
        return out;
    }

    public void writeToFileRGB(String path) throws FileNotFoundException {
        File f = new File(path);
        PrintWriter zapis = new PrintWriter(path);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                zapis.println(get(i, j).getRed() + " " + get(i, j).getGreen() + " " + get(i, j).getBlue());
            }
        }
        zapis.close();
    }

    public void writeToFileRGBY(String path) throws FileNotFoundException {
        File f = new File(path);
        PrintWriter zapis = new PrintWriter(path);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //zapis.println(get(i, j).getRed() + " " + get(i, j).getGreen() + " " +
                //        get(i, j).getBlue() + " " + RGBtoHSV(get(i, j).getRed(), get(i, j).getGreen(), get(i, j).getBlue())[0]);
                zapis.println(get(i, j).getRed() + " " + get(i, j).getGreen() + " "
                        + get(i, j).getBlue() + " " + (get(i, j).getRed() + get(i, j).getGreen()
                        + get(i, j).getBlue()) / 3);

            }
        }
        zapis.close();
    }

    public static Picture drawBoard(int size, int point) {
        Picture out = new Picture(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i / point) % 2 == 0 && (j / point) % 2 == 1
                        || (i / point) % 2 == 1 && (j / point) % 2 == 0) {
                    out.set(i, j, Color.black);
                } else {
                    out.set(i, j, Color.white);
                }
            }
        }
        return out;
    }

    public static Picture[] RGBtoHSV(Picture rgb) {

        double h, s, v;

        double r, g, b;
        double min, max, delta;

        Picture hue = new Picture(rgb.width(), rgb.height()),
                sat = new Picture(rgb.width(), rgb.height()),
                val = new Picture(rgb.width(), rgb.height());

        for (int i = 0; i < rgb.width(); i++) {
            for (int j = 0; j < rgb.height(); j++) {
                r = rgb.get(i, j).getRed();
                g = rgb.get(i, j).getGreen();
                b = rgb.get(i, j).getBlue();

                min = Math.min(Math.min(r, g), b);
                max = Math.max(Math.max(r, g), b);

                // V
                v = max;

                delta = max - min;

                // S
                if (max != 0) {
                    s = delta / max;
                    // H
                    if (r == max) {
                        h = (g - b) / delta; // between yellow & magenta
                    } else if (g == max) {
                        h = 2 + (b - r) / delta; // between cyan & yellow
                    } else {
                        h = 4 + (r - g) / delta; // between magenta & cyan
                    }
                    h *= 42.5;    // degrees

                    if (h < 0) {
                        h += 255;
                    }
                } else {
                    s = 0;
                    h = 0;
                }

                s *= 255;
                hue.set(i, j, new Color((int) h, (int) h, (int) h));
                sat.set(i, j, new Color((int) s, (int) s, (int) s));
                val.set(i, j, new Color((int) v, (int) v, (int) v));
            }
        }

        return new Picture[]{hue, sat, val};
    }

    public static Picture HSVtoRGB(Picture hue, Picture sat, Picture val) {

        double h, s, v;

        double r, g, b;
        double m, c, x;

        Picture out = new Picture(hue.width(), hue.height());

        for (int i = 0; i < out.width(); i++) {
            for (int j = 0; j < out.height(); j++) {
                h = hue.get(i, j).getRed();
                s = sat.get(i, j).getGreen();
                v = val.get(i, j).getBlue();

                s /= 255;
                h /= 42.5;

                c = v * s;
                x = c * (1 - Math.abs(h % 2 - 1));

                if (h < 1) {
                    r = c;
                    g = x;
                    b = 0;
                } else if (h < 2) {
                    r = x;
                    g = c;
                    b = 0;
                } else if (h < 3) {
                    r = 0;
                    g = c;
                    b = x;
                } else if (h < 4) {
                    r = 0;
                    g = x;
                    b = c;
                } else if (h < 5) {
                    r = x;
                    g = 0;
                    b = c;
                } else if (h < 6) {
                    r = c;
                    g = 0;
                    b = x;
                } else {
                    r = 0;
                    g = 0;
                    b = 0;
                }

                m = v - c;
                r += m;
                g += m;
                b += m;

                out.set(i, j, new Color((int) r, (int) g, (int) b));
            }
        }

        return out;
    }

    public Picture negative() {
        Picture out = new Picture(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                out.set(i, j, new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue()));
            }
        }
        return out;
    }

    public Picture sepia(int depth, int offs) {
        Picture out = Picture.RGBtoHSV(this)[2];
        int r1, g1, b1;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = out.get(i, j);

                r1 = 2 * depth + c.getRed() - offs;
                if (r1 > 255) {
                    r1 = 255;
                }
                if (r1 < 0) {
                    r1 = 0;
                }
                g1 = depth + c.getGreen() - offs;
                if (g1 > 255) {
                    g1 = 255;
                }
                if (g1 < 0) {
                    g1 = 0;
                }
                b1 = c.getBlue() - offs;
                if (b1 > 255) {
                    b1 = 255;
                }
                if (b1 < 0) {
                    b1 = 0;
                }
                out.set(i, j, new Color(r1, g1, b1));
            }
        }
        return out;
    }

    public Picture level(int value) {
        Picture out = Picture.RGBtoHSV(this)[2];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = out.get(i, j);
                if (c.getBlue() > value) {
                    out.set(i, j, c.WHITE);
                } else {
                    out.set(i, j, c.BLACK);
                }
            }
        }
        return out;
    }

    public Picture getGreenThings(int value) {
        Picture out = new Picture(this.width, this.height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = this.get(i, j);
                if (c.getGreen() > value) {
                    out.set(i, j, c);
                }
            }
        }
        return out;
    }

    public Picture rotate(double kont) {
        Picture out = new Picture(width, height);
        double x, y;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //x = (i-width/2)*Math.cos(Math.toRadians(kont))-(j-height/2)*Math.sin(Math.toRadians(kont))+width/2;
                //y = (i-width/2)*Math.sin(Math.toRadians(kont))+(j-height/2)*Math.sin(Math.toRadians(kont))+height/2;
                x = i * Math.cos(Math.toRadians(kont)) - j * Math.sin(Math.toRadians(kont));
                y = i * Math.sin(Math.toRadians(kont)) + j * Math.sin(Math.toRadians(kont));
                out.set(i, j, this.get((int) x, (int) y));
            }
        }
        return out;
    }

    public Picture light_levels(int b) {
        Picture out = new Picture(width, height);
        int delta = 2;
        int[] lut = new int[256];
        for (int i = 1; i < b; i++) {
            delta *= 2;
        }
        delta = 256 / delta;
        for (int i = 0; i < 256; i++) {
            lut[i] = Math.round((i - delta / 2 - 1) / delta) * delta + (delta / 2 - 1);
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                out.set(i, j, new Color(lut[c.getRed()], lut[c.getGreen()], lut[c.getBlue()]));
            }
        }
        return out;
    }

    public Picture move(int x, int y) {
        Picture out = new Picture(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                out.set(i, j, this.get((i + x) % width, (j + y) % height));
            }
        }
        return out;
    }

    public Picture duplicate(int n) {
        Picture out = new Picture(width * n, height * n);

        for (int in = 0; in < n; in++) {
            for (int jn = 0; jn < n; jn++) {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        out.set(i + in * width, j + jn * height, get(i, j));
                    }
                }
            }
        }

        return out;
    }

    public Picture plus(Picture pic) {
        Picture out = new Picture(width, height);
        int r, g, b;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                Color c1 = pic.get(i, j);
                r = c.getRed() + c1.getRed();
                g = c.getGreen() + c1.getGreen();
                b = c.getBlue() + c1.getBlue();
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }

    public Picture minus(Picture pic) {
        Picture out = new Picture(width, height);
        int r, g, b;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                Color c1 = pic.get(i, j);
                r = c.getRed() - c1.getRed();
                g = c.getGreen() - c1.getGreen();
                b = c.getBlue() - c1.getBlue();
                if (r < 0) {
                    r = 0;
                }
                if (g < 0) {
                    g = 0;
                }
                if (b < 0) {
                    b = 0;
                }
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }

    public Picture times(Picture pic) {
        Picture out = new Picture(width, height);
        int r, g, b;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                Color c1 = pic.get(i, j);
                r = c.getRed() * c1.getRed();
                g = c.getGreen() * c1.getGreen();
                b = c.getBlue() * c1.getBlue();
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }

    public Picture divide(Picture pic) {
        Picture out = new Picture(width, height);
        int r, g, b;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                Color c1 = pic.get(i, j);
                if (c1.getRed() != 0) {
                    r = c.getRed() / c1.getRed();
                } else if (c.getRed() != 0) {
                    r = 255;
                } else {
                    r = 1;
                }
                if (c1.getGreen() != 0) {
                    g = c.getGreen() / c1.getGreen();
                } else if (c.getGreen() != 0) {
                    g = 255;
                } else {
                    g = 1;
                }
                if (c1.getBlue() != 0) {
                    b = c.getBlue() / c1.getBlue();
                } else if (c.getBlue() != 0) {
                    b = 255;
                } else {
                    b = 1;
                }
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }

    public Picture divide2(Picture pic) {
        Picture out = new Picture(width, height);
        int r, g, b;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                Color c1 = pic.get(i, j);
                if (c1.getRed() != 0) {
                    r = c.getRed() / c1.getRed();
                    if (r != 1) {
                        r = 255 - r;
                    }
                } else if (c.getRed() != 0) {
                    r = 255;
                } else {
                    r = 1;
                }
                if (c1.getGreen() != 0) {
                    g = c.getGreen() / c1.getGreen();
                    if (g != 1) {
                        g = 255 - g;
                    }
                } else if (c.getGreen() != 0) {
                    g = 255;
                } else {
                    g = 1;
                }
                if (c1.getBlue() != 0) {
                    b = c.getBlue() / c1.getBlue();
                    if (r != 1) {
                        b = 255 - b;
                    }
                } else if (c.getBlue() != 0) {
                    b = 255;
                } else {
                    b = 1;
                }
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }

    public Picture power() {
        Picture out = new Picture(width, height);
        int r, g, b;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                r = c.getRed() * c.getRed();
                g = c.getGreen() * c.getGreen();
                b = c.getBlue() * c.getBlue();
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }

    public Picture sqrt() {
        Picture out = new Picture(width, height);
        int r, g, b;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = get(i, j);
                r = (int) Math.sqrt(c.getRed());
                g = (int) Math.sqrt(c.getGreen());
                b = (int) Math.sqrt(c.getBlue());
                out.set(i, j, new Color(r, g, b));
            }
        }

        return out;
    }

    public Picture finddiff(Picture sec) {
        Picture out = new Picture(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!this.get(i, j).equals(sec.get(i, j))) {
                    /*for(int ii = i - 4; ii < i + 4; ii++){
                    for(int jj = j - 4; jj < j + 4; jj++){
                    if(ii>=0&&jj>=0&&ii<width&&jj<height){
                    out.set(ii, jj, Color.red);
                    }
                    }
                    }
                     *
                     */
                    out.set(i, j, Color.red);
                }
            }
        }
        return out;
    }

    /**
     * Return a JLabel containing this Picture, for embedding in a JPanel,
     * JFrame or other GUI widget.
     */
    public JLabel getJLabel() {
        if (image == null) {
            return null;
        }         // no image available
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }

    /**
     * Set the origin to be the upper left pixel.
     */
    public void setOriginUpperLeft() {
        isOriginUpperLeft = true;
    }

    /**
     * Set the origin to be the lower left pixel.
     */
    public void setOriginLowerLeft() {
        isOriginUpperLeft = false;
    }

    /**
     * Display the picture in a window on the screen.
     */
    public void show() {

        // create the GUI for viewing the image if needed
        if (frame == null) {
            frame = new JFrame();

            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menuBar.add(menu);
            JMenuItem menuItem1 = new JMenuItem(" Save...   ");
            menuItem1.addActionListener(this);
            menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            menu.add(menuItem1);
            frame.setJMenuBar(menuBar);



            frame.setContentPane(getJLabel());
            // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle(filename);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }

        // draw
        frame.repaint();
    }

    /**
     * Return the height of the picture in pixels.
     */
    public int height() {
        return height;
    }

    /**
     * Return the width of the picture in pixels.
     */
    public int width() {
        return width;
    }

    /**
     * Return the color of pixel (i, j).
     */
    public Color get(int i, int j) {
        if (i < width && j < height && i > -1 && j > -1) {
            if (isOriginUpperLeft) {
                return new Color(image.getRGB(i, j));
            } else {
                return new Color(image.getRGB(i, height - j - 1));
            }
        } else {
            return Color.white;
        }
    }

    /**
     * Set the color of pixel (i, j) to c.
     */
    public void set(int i, int j, Color c) {
        if (c == null) {
            throw new RuntimeException("can't set Color to null");
        }
        if (isOriginUpperLeft) {
            image.setRGB(i, j, c.getRGB());
        } else {
            image.setRGB(i, height - j - 1, c.getRGB());
        }
    }

    /**
     * Is this Picture equal to obj?
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Picture that = (Picture) obj;
        if (this.width() != that.width()) {
            return false;
        }
        if (this.height() != that.height()) {
            return false;
        }
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                if (!this.get(x, y).equals(that.get(x, y))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Save the picture to a file in a standard image format.
     * The filetype must be .png or .jpg.
     */
    public void save(String name) {
        save(new File(name));
    }

    /**
     * Save the picture to a file in a standard image format.
     */
    public void save(File file) {
        this.filename = file.getName();
        if (frame != null) {
            frame.setTitle(filename);
        }
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        suffix = suffix.toLowerCase();
        if (suffix.equals("jpg") || suffix.equals("png")) {
            try {
                ImageIO.write(image, suffix, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: filename must end in .jpg or .png");
        }
    }

    /**
     * Opens a save dialog box when the user selects "Save As" from the menu.
     */
    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame,
                "Use a .png or .jpg extension", FileDialog.SAVE);
        chooser.setVisible(true);
        if (chooser.getFile() != null) {
            save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    /**
     * Test client. Reads a picture specified by the command-line argument,
     * and shows it in a window on the screen.
     */
    public static void main(String[] args) {
        Picture pic = new Picture(args[0]);
        System.out.printf("%d-by-%d\n", pic.width(), pic.height());
        pic.show();
    }
}
