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
public class RGBImage {
    private RGB[][] image;
    private String path;
    private int width;
    private int height;

    public RGBImage(String path) throws IOException {
        this.path = path;

        BufferedImage bi = ImageIO.read(new File(path));

        this.width = bi.getWidth();
        this.height = bi.getHeight();
        this.image = new RGB[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int color = bi.getRGB(i,j);
                //alpha = (byte)(color>>24);
                int red   = Byte.toUnsignedInt((byte)(color>>16));
                int green = Byte.toUnsignedInt((byte)(color>>8));
                int blue  = Byte.toUnsignedInt((byte)(color));
                this.image[i][j]  = new RGB(red, green, blue);
            }
        }
    }

    public RGBImage(RGB[][] image) {
        this.image = image.clone();
        this.path = null;
        this.width = image.length;
        this.height = (image.length == 0) ? 0 : image[0].length;
    }

    public void write(String path) throws IOException {
        BufferedImage bi = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                RGB pixel = this.image[i][j];
                int rgb = new Color(pixel.getRed(), pixel.getGreen(),pixel.getBlue()).getRGB();
                bi.setRGB(i,j,rgb);
            }
        }
        ImageIO.write(bi, "bmp", new File(path));
    }

    public RGB[][] getData() {
        return this.image;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void applyMask(MonochromeImage mask) {
        Monochrome[][] maskData = mask.getData();

        for(int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                RGB pixel = this.image[i][j];
                int currentBrightness = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                double coeff = (double)maskData[i][j].getBrightness() / (double)currentBrightness;
                int newRed = Math.min((int)((double)pixel.getRed() * coeff), 255);
                int newGreen = Math.min((int)((double)pixel.getGreen() * coeff), 255);
                int newBlue = Math.min((int)((double)pixel.getBlue() * coeff), 255);
                this.image[i][j] = new RGB(newRed, newGreen, newBlue);
            }
        }
    }
}
