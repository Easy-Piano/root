package sasha.forlearning;

import sasha.forlearning.RecordingTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by 1 on 18.04.2014.
 */
public class Dialog extends JFrame{
    private JButton recButton;
    private JButton playButton;
    private JButton reservedButton;
    private boolean playpressed;
    private boolean recpressed;
    private RecordingTest rt;
    private File f;
    private Sound snd;
    private boolean fileLinked;
    public Dialog() {
        rt=new RecordingTest();
        f=rt.getNewFile();
        playpressed=false;
        recpressed=false;
        fileLinked=false;
        recButton = new JButton();
        playButton = new JButton();
        reservedButton = new JButton();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("audio recognition inception");
        recButton.setText("StartRec");
        playButton.setText("Play");
        reservedButton.setText("Reserved");
        recButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recButtonActionPerformed(evt);
            }
        });
        playButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        reservedButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reservedButtonActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(recButton)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(reservedButton)
                                        .addComponent(playButton)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(reservedButton)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(recButton)
                                        .addComponent(playButton)
                        )
        );
        pack();
    }

    private void recButtonActionPerformed(ActionEvent evt){
        if (recpressed){
            rt.stopRecording();
            recButton.setText("StartRec");
            recpressed=false;
        }else{
            rt.startRecording();
            recButton.setText("StopRec");
            recpressed=true;
        }
    }
    private void playButtonActionPerformed(ActionEvent evt) {
        if (playpressed){
            playButton.setText("Play");
            snd.stop();
            playpressed=false;
        }else{
            if (!(fileLinked)){
                snd=new Sound(f);
            }
            snd.play();
            playButton.setText("Stop");
            playpressed=true;
        }
    }
    private void reservedButtonActionPerformed(ActionEvent evt) {
    }
}
