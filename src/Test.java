import javax.swing.*;

/**
 * Created by Дмитрий on 26.04.2014.
 */
public class Test {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Piano!");
        jf.setSize(400, 200);
        jf.add(new PianoComponent());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
