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
    private static int offset=100000;//to skip the beginning
    public static void main(String[] args){
        //String s = SashaMain.class.getClassLoader().getResource("Chopin.wav").getPath();
        String s="C:\\Users\\1\\Documents\\GitHub\\root\\src\\sasha\\forproject\\Chopin.wav";
        //String s="C:\\Users\\Дмитрий\\Documents\\GitHub\\root\\src\\sasha\\forlearning\\Chopin.wav";
        WavWrapper WW=new WavWrapper(s);
        double[] v=WW.getArray(0,offset,degree);
        double[] vv=new double[degree];
        FFT.complexToComplex(1,degree,v,vv);
        long sampleRate=WW.getSampleRate();
        XYSeries seriesmag = new XYSeries("Spectrum magnitude");
        double minfrequency=sampleRate/degree;
        for (int j=0; j<degree/2;j++){
            seriesmag.add(minfrequency*j, Math.abs(v[j]));
        }
        XYDataset xyDataset = new XYSeriesCollection(seriesmag);
        JFreeChart chart = ChartFactory.createXYLineChart("y=spectrum(j)", "frequency", "magnitude",
        xyDataset, PlotOrientation.VERTICAL, true, true, true);
        JFrame frame = new JFrame("spectrum try");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(800,600);
        frame.setVisible(true);

        XYSeries seriesphase = new XYSeries("Spectrum phase");
        for (int j=0; j<degree/2;j++){
            seriesphase.add(j*minfrequency, vv[j]);
        }
        XYDataset xyDatasetphase = new XYSeriesCollection(seriesphase);
        JFreeChart chartphase = ChartFactory.createXYLineChart("y=spectrum(j)", "frequency", "phase",
                xyDatasetphase, PlotOrientation.VERTICAL, true, true, true);
        JFrame framephase = new JFrame("spectrum try");
        framephase.getContentPane().add(new ChartPanel(chartphase));
        framephase.setSize(800,600);
        framephase.setVisible(true);
    }
}
