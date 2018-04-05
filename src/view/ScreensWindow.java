package view;

import javax.swing.*;
import java.awt.*;

public class ScreensWindow extends JFrame {

    private JPanel rootPanel;
    private JPanel pixelScreenPanel;
    private JPanel characterScreenPanel;
    private JFrame parent;

    private JScrollPane pixelScreenScrollPanel;
    private JScrollPane characterScreenScrollPanel;

    public ScreensWindow(PixelScreenView pixelScreenView, CharacterScreenView characterScreenView ,JFrame parent) {
        this.parent = parent;
        this.pixelScreenPanel = pixelScreenView;
        this.characterScreenPanel = characterScreenView;

        setTitle("Screens");
        setContentPane(rootPanel);
        setSize(265, 575);
        setResizable(false);
        setLocation(parent.getX() + parent.getSize().width, parent.getY() + 1);
        setUndecorated(true);
        setFocusableWindowState(false);
    }

    private void createUIComponents() {
        IScreenView pixelScreenView = (IScreenView) pixelScreenPanel;
        pixelScreenView.update();
        IScreenView characterScreenView = (IScreenView) characterScreenPanel;
        characterScreenView.update();
    }

    public void _show() {
        setLocation(parent.getX() + parent.getSize().width, parent.getY() + 1);
        setVisible(true);
    }

    public void _hide() {
        setVisible(false);
    }
}
