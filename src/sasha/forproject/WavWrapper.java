package sasha.forproject;

import sasha.forlearning.WavFile;

import java.io.File;
import java.util.Vector;

/**
 * Created by 1 on 26.04.2014.
 */
public class WavWrapper {
    private String path;
    private int numChannels;
    private Vector arrays;
    public WavWrapper(String s){
        path=s;
        arrays = new Vector();
        try
        {
            WavFile wavFile = WavFile.openWavFile(new File(path));
            numChannels = wavFile.getNumChannels();
            for (int i=0;i<numChannels;i++){
                arrays.add((new WavArray(wavFile,i)).getArray());
            }
            wavFile.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
    public double[] getArray(int number, int size){
        double[] A = new double[size];
        if ((number>=arrays.size())||(size>((Vector) arrays.get(0)).size())){
            System.out.println("ERROR");
        }else{
            for (int i=0; i<size;i++){
                A[i]=(double)((Vector) arrays.get(number)).get(i);
            }
        }
        return A;
    }
}
