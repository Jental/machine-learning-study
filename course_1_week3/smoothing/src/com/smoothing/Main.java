package com.smoothing;

import com.smoothing.Image.MonochromeImage;
import com.smoothing.Image.RGBImage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        RGBImage image = new RGBImage("data/mpv-shotb0001.bmp");
        image.write("out/colored.bmp");

        MonochromeImage image2 = new MonochromeImage(image);
        image2.write("out/mono.bmp");

        image2.changeBrightness(0.5);
        image.applyMask(image2);
        image.write("out/masked.bmp");
    }
}
