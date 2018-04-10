package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HelpWindow extends JFrame {
    private JPanel rootPanel;
    private JTextArea helpTextArea;

    public HelpWindow(JFrame parent) {
        setTitle("Help");
        setAlwaysOnTop(true);
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setContentPane(rootPanel);

        setLocationRelativeTo(parent);

        helpTextArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        helpTextArea.requestFocus();
        helpTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });

    }
}
