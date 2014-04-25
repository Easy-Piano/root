package sasha.forlearning;

/**
 * Created by 1 on 04.04.14.
 */
public class MyThread extends Thread{
    private String name;
    public MyThread(String n){
        name=n;
    }
    public void run(){
        Sound.playSound(name).join();
    }
}
