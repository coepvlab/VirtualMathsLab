package NewPrograms;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class PieChart {

    public static void main(String[] args) {
        // Create dataset
        PieDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Example",   // Chart title
                dataset,               // Dataset
                true,                  // Include legend
                true,
                false
        );

        // Set background color to white
        chart.setBackgroundPaint(Color.WHITE);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);

        // Save as an image
        try {
        	ChartUtils.saveChartAsPNG(new File("E:\\Nayan_Eclipse_workspace\\Images\\PieChart.png"), chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category1", 40);
        dataset.setValue("Category2", 25);
        dataset.setValue("Category3", 20);
        dataset.setValue("Category4", 15);
        return dataset;
    }
}
