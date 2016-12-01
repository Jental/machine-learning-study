package com.smoothing.Image;

import com.smoothing.Pixel.Monochrome;
import com.smoothing.Pixel.RGB;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jental on 01/12/16.
 */
public class MonochromeImage {
    private Monochrome[][] image;
    private String path;
    private int width;
    private int height;

    public MonochromeImage(String path) throws IOException {
        BufferedImage bi = ImageIO.read(new File(path));

        this.width = bi.getWidth();
        this.height = bi.getHeight();
        this.image = new Monochrome[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int color = bi.getRGB(i,j);
                //alpha = (byte)(color>>24);
                int red   = Byte.toUnsignedInt((byte)(color>>16));
                int green = Byte.toUnsignedInt((byte)(color>>8));
                int blue  = Byte.toUnsignedInt((byte)(color));
                int gray = (red + green + blue) / 3;
                this.image[i][j]  = new Monochrome(gray);
            }
        }
    }

    public MonochromeImage(Monochrome[][] image) {
        this.image = image.clone();
        this.path = null;
        this.width = image.length;
        this.height = (image.length == 0) ? 0 : image[0].length;
    }

    public MonochromeImage(RGBImage image) {
        RGB[][] data = image.getData();

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.image = new Monochrome[width][height];
        this.path = null;

        for (int i=0; i < this.width; i++) {
            for (int j=0; j < this.height; j++) {
                RGB pixel = data[i][j];
                this.image[i][j] = new Monochrome((pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3);
            }
        }
    }

    public void write(String path) throws IOException {
        BufferedImage bi = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Monochrome pixel = this.image[i][j];
                int rgb = new Color(pixel.getBrightness(), pixel.getBrightness(),pixel.getBrightness()).getRGB();
                bi.setRGB(i,j,rgb);
            }
        }
        ImageIO.write(bi, "bmp", new File(path));
    }

    public Monochrome[][] getData() {
        return this.image;
    }

    public int[][] getIntData() {
        int[][] result = new int[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                result[i][j] = this.image[i][j].getBrightness();
            }
        }

        return result;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void changeBrightness(double coeff) {
        for(int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Monochrome pixel = this.image[i][j];

                this.image[i][j] = new Monochrome(Math.min((int)((double)pixel.getBrightness() * coeff), 255));
            }
        }
    }
}
