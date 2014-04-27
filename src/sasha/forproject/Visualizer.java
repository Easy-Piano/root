package sasha.forproject;

/**
 * Created by 1 on 26.04.2014.
 */
public interface Visualizer {
    //****************************************CONSTANTS********************
    public static final int STEP = 1;
    public static final int MAX = 1000000;
    public final static int N = 2048;
    //***********************************************************
    public void visualize(double[] array, long n);
}
