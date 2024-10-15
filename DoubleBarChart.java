package NewPrograms;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class DoubleBarChart {

    public static void main(String[] args) {
        // Create dataset
        CategoryDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Double Bar Chart Example",  // Chart title
                "Category",                  // X-axis Label
                "Value",                     // Y-axis Label
                dataset,                     // Dataset
                PlotOrientation.VERTICAL,    // Plot orientation
                true,                        // Include legend
                true,
                false
        );

        // Set background color to white
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(Color.WHITE);

        // Save as an image
        try {
        	ChartUtils.saveChartAsPNG(new File("E:\\Nayan_Eclipse_workspace\\Images\\DoubleBarChart.png"), chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Series 1
        dataset.addValue(8, "Series1", "Category1");
        dataset.addValue(10, "Series1", "Category2");
        dataset.addValue(15, "Series1", "Category3");
        dataset.addValue(20, "Series1", "Category4");

        // Series 2
        dataset.addValue(12, "Series2", "Category1");
        dataset.addValue(18, "Series2", "Category2");
        dataset.addValue(10, "Series2", "Category3");
        dataset.addValue(25, "Series2", "Category4");

        return dataset;
    }
}
