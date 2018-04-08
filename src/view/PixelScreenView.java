package view;

import javax.swing.*;
import java.awt.*;

class PixelScreenView extends JPanel implements IScreenView {

    private int pixelSize;
    private int[][] memory;

    public PixelScreenView(int weight, int height, int pixelSize) {
        this.pixelSize = pixelSize;
        this.memory = new int[256][256];
        setSize(weight * pixelSize, height * pixelSize);
        setVisible(true);
    }

    @Override
    public void setColorData(int[][] data) {
        memory = data;
    }

    @Override
    public void setCharData(int[][] data) {}

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if (memory != null) {
            for (int i = 0; i < memory.length; ++i) {
                for (int j = 0; j < memory[i].length; ++j) {
                    graphics2D.setColor(getColorByByte(memory[i][j]));
                    graphics2D.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                }
            }
        }
    }

    private Color getColorByByte(int colorCode) {
        int r = (colorCode & 0b11000000) >> 6;
        int g = (colorCode & 0b00111000) >> 3;
        int b = (colorCode & 0b00000111);
        return new Color(85 * r, 31 * g, 31 * b);
    }
}