package safich.montecarlo.main.control;

public class Analyzer {
    public static double getMaxValue(double[] array) {
        double max = array[0];
        for (int i = 0; i < array.length; i++) {
            if(max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    public static double getMinValue(double[] array) {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if(min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }

    public static double getAverage(double[] array) {
        double average = 0;
        double sum = 0;
        for (int j = 0; j < array.length; j++) {
            sum += array[j];
        }
        average = sum/array.length;
        return average;
    }
}
