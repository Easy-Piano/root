/**
 * Created by Дмитрий on 18.04.14.
 */
import javax.swing.*;
import java.awt.*;

class PianoComponent extends JPanel {

    PianoComponent() {

        setLayout(null);

        for (int i = 0; i < 20; i++) {
            JButton key = new JButton();
            key.setBackground(Color.WHITE);
            key.setLocation(i * 20, 0);
            key.setSize(20, 120);
            add(key);
            setComponentZOrder(key, i);
        }

        for (int i = 0; i < 20; i++) {
            int j = i % 7;
            if (j == 2 || j == 6)
                continue;

            JButton key = new JButton();
            key.setBackground(Color.BLACK);
            key.setLocation(i * 20 + 12, 0);
            key.setSize(16, 80);
            add(key);
            setComponentZOrder(key, 0);
        }
    }
}

