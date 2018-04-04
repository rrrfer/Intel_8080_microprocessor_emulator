package view;

import javax.swing.*;

public class ScreensWindow extends JFrame {

    private JPanel rootPanel;
    private JPanel pixelScreenPanel;
    private JFrame parent;

    public ScreensWindow(PixelScreenView pixelScreenView, JFrame parent) {
        this.parent = parent;
        this.pixelScreenPanel = pixelScreenView;
        setTitle("Screens");
        setContentPane(rootPanel);
        pack();
        setResizable(false);
        setLocation(parent.getX() + parent.getSize().width, parent.getY());
        setFocusableWindowState(false);
        setFocusable(false);
        setAlwaysOnTop(true);
    }

    private void createUIComponents() {
        IScreenView pixelScreenView = (IScreenView) pixelScreenPanel;
        pixelScreenView.update();
    }

    public void _show() {
        setLocation(parent.getX() + parent.getSize().width, parent.getY());
        setVisible(true);
    }

    public void _hide() {
        setVisible(false);
    }
}
