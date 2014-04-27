package sasha.forproject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

/**
 * Created by 1 on 26.04.2014.
 */
public class SignalVisualizer implements Visualizer {
    private static int numChannel=0;
    //****************************************CONSTANTS********************
    private static final int step = 1;
    private static final int max = 1000000;
    //*********************************************************************
    public void visualize(double[] array, long sampleRate){
        //*******************************FOR VISUALISATION************
        numChannel++;
        XYSeries series = new XYSeries("Magnitude(time) for channel # "+((Integer)(numChannel)).toString());
        double i;//to track time coordinate
        int mymax=(max < array.length ? max : array.length);
        for (i=0; i<mymax; i+=step){
            series.add(i/sampleRate,array[(int)i]);
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("y=magnitude(time)", "time", "magnitude",
                xyDataset, PlotOrientation.VERTICAL, true, true, true);
        JFrame frame = new JFrame(((Integer)(numChannel)).toString());
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(800,600);
        frame.setVisible(true);
        //************************************************************
    }
}
