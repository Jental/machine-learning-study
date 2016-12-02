package com.smoothing;

import com.smoothing.Image.MonochromeImage;
import com.smoothing.Pixel.RGB;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by jental on 01/12/16.
 */
public class ImageProcessor {

    // Finds minimum for the given point with the random search algorithm.
    private static int getMinimum_RandomSearch(RGB[][] image, int x, int y, double d) {
        throw new NotImplementedException();
        // На первом шаге выбирается вектор u из равномерного распределения на единичной сфере.
        // На втором шаге вычисляется значение f(x + d*u) и производится сдвиг в направлении вектора u на величину:
        // (f(x) − f(x + d*u)) / d
    }

    // For every point finds minimum for the given point with the random search algorithm.
    public MonochromeImage minimize_RandomSearch(int d) {
        throw new NotImplementedException();
    }
}
