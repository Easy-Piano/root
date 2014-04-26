package sasha.forproject;

import sasha.forlearning.WavFile;

import java.io.File;
import java.util.Vector;

/**
 * Created by 1 on 26.04.2014.
 */
public class WavWrapper {
    private final int SIZE=10000;
    private final int OFFSET=1000;
    private String path;
    private int numChannels;
    private long sampleRate;//Hz
    private Vector arrays;
    public WavWrapper(String s){
        path=s;
        try
        {
            WavFile wavFile = WavFile.openWavFile(new File(path));
            numChannels = wavFile.getNumChannels();
            sampleRate = wavFile.getSampleRate();
            arrays=((new WavArray(wavFile)).getArrays());
            wavFile.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        SignalVisualizer SV=new SignalVisualizer();
        for (int i=0; i<numChannels; i++){
            SV.visualize(getArray(i,OFFSET,SIZE),sampleRate);
        }
    }
    public double[] getArray(int number,int offset, int size){
        double[] A = new double[size];
        if ((number>=arrays.size())||((size+offset)>(((Vector) arrays.get(number)).size()))){
            System.out.println("ERROR");
        }else{
            int max=offset+size;
            int j=0;
            for (int i=offset;i<max;i++,j++){
                A[j]=(double)((Vector) arrays.get(number)).get(i);
            }
        }
        return A;
    }
    public long getSampleRate(){
        return sampleRate;
    }
}
