package com.company.main.control;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GraphicBuilder {
    public void build(double[][] array) throws IOException {
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        double[] array2 = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            array2[i] = array[i][0];
        }
        Arrays.sort(array2);

        dataset.addSeries("Values", array2, 25);
        String plotTitle = "Распределение";
        String xAxis = "Значение";
        String yAxis = "Частота";
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        int width = 500;
        int height = 300;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xAxis, yAxis, dataset, orientation, false, false, false);
        ChartUtils.saveChartAsPNG(new File("histogram.PNG"), chart, width, height);
    }
}
