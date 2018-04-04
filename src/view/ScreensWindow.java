package view;

import javax.swing.*;

public class ScreensWindow extends JFrame {

    private JPanel rootPanel;
    private JPanel pixelScreenPanel;
    private JPanel characterScreenPanel;
    private JFrame parent;

    public ScreensWindow(PixelScreenView pixelScreenView, JFrame parent) {
        this.parent = parent;
        this.pixelScreenPanel = pixelScreenView;
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
        characterScreenPanel = new PixelScreenView(256, 256, 1);
        IScreenView characterScreenView = (IScreenView) characterScreenPanel;
        characterScreenView.setData(new int[256][256]);
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
