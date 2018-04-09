package view;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AboutWindow extends JFrame {

    private JPanel rootPanel;
    private JTextArea aboutTextArea;

    public AboutWindow(JFrame parent) {
        setTitle("About");
        setAlwaysOnTop(true);
        setSize(200, 90);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setContentPane(rootPanel);

        setLocationRelativeTo(parent);

        aboutTextArea.setFont(MainWindow.mainFont);
        aboutTextArea.requestFocus();
        aboutTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });
    }
}
