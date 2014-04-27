package sasha.forproject;

import javax.sound.midi.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 1 on 26.04.2014.
 */
public class SashaMain {//shows spectrum finally
    private final static int SIZE=5000;
    private final static int OFFSET=20500;//to skip the beggining
    public static void main(String[] args){
        Notes Nclass;
        Nclass=new Notes();
        //String s = SashaMain.class.getClassLoader().getResource("Chopin.wav").getPath();
        //String s="C:\\Users\\1\\Documents\\GitHub\\root\\src\\sasha\\forproject\\Chopin.wav";
        String s="C:\\Users\\1\\Documents\\GitHub\\root\\src\\sasha\\forproject\\samples_1.wav";//10000++ nuisance
        //String s="C:\\Users\\Дмитрий\\Documents\\GitHub\\root\\src\\sasha\\forlearning\\Chopin.wav";
        //Graphs
        WavWrapper WW=new WavWrapper(s);
        long sampleRate=WW.getSampleRate();
        SignalVisualizer SV=new SignalVisualizer();
        for (int i=0; i<WW.getNumChannels(); i++){
            SV.visualize(WW.getArray(i, OFFSET, SIZE),sampleRate);
        }
        double[] signal=WW.getArray(0,OFFSET,Visualizer.N);
        double[] spectrum=FFT.RealToReal(signal);
        FourierVisualizer FV = new FourierVisualizer();
        FV.visualize(spectrum,sampleRate);
        //////////////////////
        //Music 1
        try {
            Sequence sequence = MidiSystem.getSequence(new File("C:\\Users\\1\\Documents\\GitHub\\root\\src\\" +
                    "sasha\\forproject\\yiruma-river_flows_in_you.mid"));
            Synthesizer synth;
            MidiChannel[] channels;

            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();
            channels[0].programChange(1);//Acoustic Royal
        } catch (Exception e) {
            System.err.println(e);
        }

        ///////////////////////////
        /*Music 2
        int notes[][] = {{470, 81}, {230, 80}, {470, 81}, {250, -1}, {230, 80}, {470, 81}, {230, 69}, {230, 76}, {470, 81}, {230, 69}, {470, 74}, {470, 73}, {470, 74}, {470, 76}, {470, 73}, {470, 71}, {970, -1}, {230, 69}, {230, 68}, {470, 69}, {730, -1}, {230, 64}, {230, 69}, {230, 71}, {470, 73}, {970, -1}, {230, 73}, {230, 74}, {470, 76}, {730, -1}, {230, 69}, {230, 74}, {230, 73}, {470, 71}, {1450, -1}, {470, 81}, {230, 80}, {470, 81}, {250, -1}, {230, 80}, {470, 81}, {230, 69}, {230, 76}, {470, 81}, {230, 69}, {470, 74}, {470, 73}, {470, 74}, {470, 76}, {470, 73}, {470, 71}, {970, -1}, {230, 69}, {230, 68}, {470, 69}, {730, -1}, {230, 64}, {230, 69}, {230, 71}, {470, 73}, {970, -1}, {230, 73}, {230, 74}, {470, 76}, {730, -1}, {230, 69}, {230, 74}, {230, 73}, {470, 71}, {250, -1}};
        MidiPlayer midiPlayer = new MidiPlayer();
        for (int[] note : notes) {
            if (note[1] != -1) {
                midiPlayer.playSound(0, note[0], 80, note[1]);
            } else {
                try {
                    Thread.sleep(note[0]);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SashaMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        midiPlayer.close();//after close nothing wants to work, close it only in the end*/
        ////////////////////////
        /***************************************************Find out if +1 and -1 are really back and forth
         int degree=Visualiser.N;
         double[] catchmistake = new double[degree];
         double[] zeros = new double[degree];
         for (int i=0; i<degree; i++) {catchmistake[i]=signal[i];zeros[i]=0;}
        SV.visualize(catchmistake,sampleRate);
        FFT.complexToComplex(-1,degree,catchmistake,zeros);
        FV.visualize(catchmistake,sampleRate);
        FFT.complexToComplex(1,degree,catchmistake,zeros);
        SV.visualize(catchmistake,sampleRate);**************/
    }
}
