package view;

import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class AboutWindow extends JFrame {

    private JPanel rootPanel;
    private JTextArea aboutTextArea;

    public AboutWindow(JFrame parent) {
        setTitle("About");
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(238, 107);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setContentPane(rootPanel);

        rootPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        setLocationRelativeTo(parent);

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
    }
}
