package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AboutWindow extends JFrame {

    private JPanel rootPanel;
    private JTextArea aboutTextArea;

    AboutWindow() {
        setTitle("About");
        setAlwaysOnTop(true);
        setUndecorated(true);
        //setSize(267, 165);
        //setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setContentPane(rootPanel);

        setLocationRelativeTo(null);

        aboutTextArea.setForeground(Color.WHITE);
        aboutTextArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        aboutTextArea.requestFocus();
        aboutTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });

        pack();
    }
}
