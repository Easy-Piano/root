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
    private static final int degree=512;//connected with FourierVisualizer.N (N=degree)
    private static int offset=100000;//to skip the beginning
    public static void main(String[] args){
        //String s = SashaMain.class.getClassLoader().getResource("Chopin.wav").getPath();
        String s="C:\\Users\\1\\Documents\\GitHub\\root\\src\\sasha\\forproject\\Chopin.wav";
        //String s="C:\\Users\\Дмитрий\\Documents\\GitHub\\root\\src\\sasha\\forlearning\\Chopin.wav";
        WavWrapper WW=new WavWrapper(s);
        double[] v=WW.getArray(0,offset,degree);
        double[] vv=MyFFT.RealToReal(v);
        //FFT.complexToComplex(1,degree,v,vv);//should i use 1 or -1??
        long sampleRate=WW.getSampleRate();
        FourierVisualizer FV = new FourierVisualizer();
        FV.visualize(vv,sampleRate);
    }
}
