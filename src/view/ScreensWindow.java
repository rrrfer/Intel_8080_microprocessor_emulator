package view;

import javax.swing.*;

public class ScreensWindow extends JFrame {

    private JPanel rootPanel;
    private JPanel pixelScreenPanel;
    private JPanel characterScreenPanel;
    private JFrame parent;

    ScreensWindow(PixelScreenView pixelScreenView, CharacterScreenView characterScreenView ,JFrame parent) {
        this.parent = parent;
        this.pixelScreenPanel = pixelScreenView;
        this.characterScreenPanel = characterScreenView;

        setTitle("Screens");
        setContentPane(rootPanel);
        setSize(267, 575);
        setResizable(false);
        setUndecorated(true);
        setFocusableWindowState(false);
    }

    private void createUIComponents() {
        IScreenView pixelScreenView = (IScreenView) pixelScreenPanel;
        pixelScreenView.update();
        IScreenView characterScreenView = (IScreenView) characterScreenPanel;
        characterScreenView.update();
    }

    void _show() {
        setLocation(parent.getX() + parent.getSize().width, parent.getY() + 1);
        setVisible(true);
    }

    void _hide() {
        setVisible(false);
    }
}
