package sasha.forlearning;

import java.io.File;

/**
 * Created by 1 on 04.04.14.
 */
public class Main {
    public static void main(String args[]){
        /*
        MyThread t[]=new MyThread[2];
        String path = Main.class.getClassLoader().getResource("Chopin.wav").getPath();//path from src
        t[0]=new MyThread(path);
        t[1]=new MyThread("C:\\Users\\1\\IdeaProjects\\myaudio\\src\\Chopin.wav");
        t[0].start();
        try{
            Thread.currentThread().sleep(5000);
            t[1].start();
            //Sound.playSound("C:\\Users\\1\\IdeaProjects\\myaudio\\src\\Chopin.wav").join();
        }catch(InterruptedException exc ){
        };
        */
        //Sound.playSound("C:\\Users\\1\\IdeaProjects\\myaudio\\src\\Shopen.mp3").join();//doesn't work with mp3

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dialog().setVisible(true);
            }
        });
    }
}
