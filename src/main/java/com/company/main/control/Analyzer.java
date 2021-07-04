package com.company.main.control;

import java.util.Arrays;
import java.util.Comparator;

public class Analyzer {
    public static double getMaxValue(double[][] array) {
        double max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            if(max < array[i][0]) {
                max = array[i][0];
            }
        }
        return max;
    }

    public static double getMinValue(double[][] array) {
        double min = array[0][0];
        for (int i = 0; i < array.length; i++) {
            if(min > array[i][0]) {
                min = array[i][0];
            }
        }
        return min;
    }

    public static void sortArr(double[][] array) {
        Arrays.sort(array, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[0] > o2[0]) return 1;
                else return -1;
            }
        });
    }

    public static double getAverage(double[][] array) {
        double average = 0;
        double sum = 0;
        for (int j = 0; j < array.length; j++) {
            sum += array[j][0];
        }
        average = sum/array.length;
        return average;
    }
}
