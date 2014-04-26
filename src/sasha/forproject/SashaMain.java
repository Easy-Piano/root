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
public class SashaMain {//shows spectrum finally
    private final static int degree=256;
    private static int offset=1000;//to avoid the beginning
    public static void main(String[] args){
         String s="C:\\Users\\1\\IdeaProjects\\myaudio\\src\\Chopin.wav";
        //String s="C:\\Users\\Дмитрий\\Documents\\GitHub\\root\\src\\sasha\\forlearning\\Chopin.wav";
        WavWrapper WW=new WavWrapper(s);
        double[] v=WW.getArray(0,offset,degree);
        double[] vv=new double[degree];
        FFT.complexToComplex(1,degree,v,vv);

        XYSeries series = new XYSeries("Spectrum");
        for (int j=0; j<degree;j++){
            series.add(j,vv[j]);
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("y=spectrum(j)", "frequency", "magnitude",
        xyDataset, PlotOrientation.VERTICAL, true, true, true);
        JFrame frame = new JFrame("spectrum try");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(800,600);
        frame.setVisible(true);
    }
}
