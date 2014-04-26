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
    private int numChannels;
    private Vector v;//vector of vectors, one vector per channel
    public WavArray(WavFile wavFile) {
        numChannels = wavFile.getNumChannels();//equals 1 for mono and 2 for stereo
        int numChannel;
        v = new Vector();
        for (int i=0; i<numChannels;i++){
            v.add(new Vector());
        }
        double buffer[];
        buffer = new double[BUFFER_SIZE * numChannels];
        int framesRead;

        try {
            do {
                framesRead = wavFile.readFrames(buffer, BUFFER_SIZE);//stores framesRead*numChannels to buffer
                for (int s = 0; s < framesRead * numChannels; s+=numChannels) {
                        for (numChannel=0; numChannel<numChannels; numChannel++){
                            ((Vector)(v.get(numChannel))).add(buffer[s+numChannel]);
                        }
                }
            } while (framesRead != 0);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public Vector getArrays(){
        return v;
    }
}
