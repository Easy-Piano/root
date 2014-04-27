package sasha.forproject;

/**
 * Created by 1 on 27.04.2014.
 */
public class MyFFT {
    public static double[] RealToReal(double[] signal) {
        int n = signal.length;
        double[] backup = new double[n];
        double[] zeros = new double[n];
        for (int i = 0; i < n; i++) {
            backup[i] = signal[i];
            zeros[i] = 0;
        }
        FFT.complexToComplex(1,n,backup,zeros);//-1, 1, 10 as first parameter. which one to choose?
        for (int i=0; i<n; i++){
            backup[i]=Math.sqrt(backup[i]*backup[i]+zeros[i]*zeros[i]);
        }
        return backup;
    }
}
