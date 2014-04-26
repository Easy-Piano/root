package sasha.forproject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import sasha.forlearning.WavFile;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by 1 on 25.04.2014.
 */
public class WavArray {
    private final int BUFFER_SIZE=100;//Constant
    private String path;
    private long sampleRate;//Hz
    private int numChannels;
    private int numChannel;
    private Vector v;//vector of vectors, one vector per channel
    public WavArray(WavFile wavFile, int n) {
        sampleRate = wavFile.getSampleRate();
        numChannels = wavFile.getNumChannels();//equals 1 for mono and 2 for stereo
        numChannel = n;//min is 0, max is numChannels-1
        v = new Vector();

        double buffer[];
        buffer = new double[BUFFER_SIZE * numChannels];
        int framesRead;
        //*******************************FOR VISUALISATION************
        XYSeries series = new XYSeries("Magnitude(time) for channel # "+((Integer)(numChannel+1)).toString());
        double i=0;//to track time coordinate
        //************************************************************
        try {
            do {
                framesRead = wavFile.readFrames(buffer, BUFFER_SIZE);//stores framesRead*numChannels to buffer
                for (int s = numChannel; s < framesRead * numChannels; s+=numChannels) {
                        v.add(buffer[s]);
                        //*******************************FOR VISUALISATION************
                        if (i<100000) {
                            series.add(i / sampleRate, buffer[s]);
                            i++;
                        }
                        i++;
                        //************************************************************
                }
            } while (framesRead != 0);
        } catch (Exception e) {
            System.err.println(e);
        }
        //*******************************FOR VISUALISATION************
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("y=magnitude(time)","time","magnitude",
                xyDataset,PlotOrientation.VERTICAL,true,true,true);
        JFrame frame = new JFrame(((Integer)(numChannel+1)).toString());
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(800,600);
        frame.setVisible(true);
        //************************************************************
    }
    public Vector getArray(){
        return v;
    }
    public long getSampleRate(){
        return sampleRate;
    }
}
