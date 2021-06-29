package com.company.main.control;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import java.io.File;
import java.io.IOException;

public class GraphicBuilder {
    public void build(double[] array, int number) throws IOException {
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        dataset.addSeries("Histogram", array, number);
        String plotTitle = "Distribution";
        String xAxis = "number";
        String yAxis = "value";
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        int width = 500;
        int height = 300;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xAxis, yAxis,
                dataset, orientation, false, false, false);
        ChartUtils.saveChartAsPNG(new File("histogram.PNG"), chart, width, height);
    }
}
