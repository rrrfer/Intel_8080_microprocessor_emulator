package view;

import javax.swing.*;
import java.awt.*;

class CharacterScreenView extends JPanel implements IScreenView {

    private int pixelSize;
    private int[][] colorMemory;
    private int[][] charMemory;

    public CharacterScreenView(int weight, int height, int pixelSize) {
        this.pixelSize = pixelSize;
        colorMemory = new int[20][20];
        charMemory = new int[20][20];
        setSize(weight, height);
        setVisible(true);
    }

    @Override
    public void setColorData(int[][] data) {
        colorMemory = data;
    }

    @Override
    public void setCharData(int[][] data) {
        charMemory = data;
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        for (int i = 0; i < colorMemory.length; ++i) {
            for (int j = 0; j < colorMemory[i].length; ++j) {
                graphics2D.setColor(chooseColorByCode(colorMemory[i][j]));
                graphics2D.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                graphics2D.setColor(chooseCharColorByCode(colorMemory[i][j]));
                graphics2D.drawString(String.valueOf((char)chooseCharacter(charMemory[i][j])),
                        j * pixelSize, i *pixelSize + 12);
            }
        }
    }

    private Color chooseColorByCode(int code) {
        code = code & 0b01110000;
        code = code >> 4;
        switch (code) {
            case 1: return Color.GREEN;
            case 2: return Color.BLUE;
            case 3: return Color.CYAN;
            case 4: return Color.RED;
            case 5: return Color.MAGENTA;
            case 6: return Color.ORANGE;
            case 7: return Color.DARK_GRAY;
            default: return Color.BLACK;
        }
    }

    private Color chooseCharColorByCode(int code) {
        code = code & 0b00001111;
        switch (code) {
            case 1: return Color.WHITE;
            case 2: return Color.LIGHT_GRAY;
            case 3: return Color.GRAY;
            case 4: return Color.DARK_GRAY;
            case 5: return Color.GREEN;
            case 6: return Color.ORANGE;
            case 7: return Color.BLUE;
            case 8: return Color.MAGENTA;
            case 9: return Color.CYAN;
            case 10: return Color.PINK;
            case 11: return Color.RED;
            case 12: return Color.YELLOW;
            case 13: return Color.BLACK;
            case 14: return Color.BLACK;
            case 15: return Color.BLACK;
            default: return Color.BLACK;
        }
    }

    private int chooseCharacter(int numberAsc) {
        if (numberAsc < 128) {
            return numberAsc;
        } else {
            switch (numberAsc) {
                case 128: return 1026;
                case 129: return 1027;
                case 130: return 8218;
                case 131: return 1107;
                case 132: return 8222;
                case 133: return 8230;
                case 134: return 8224;
                case 135: return 8225;
                case 136: return 8364;
                case 137: return 8240;
                case 138: return 1033;
                case 139: return 0;
                case 140: return 1034;
                case 141: return 1036;
                case 142: return 1035;
                case 143: return 1039;
                case 144: return 1106;
                case 145: return 8216;
                case 146: return 8217;
                case 147: return 8220;
                case 148: return 8221;
                case 149: return 8226;
                case 150: return 8211;
                case 151: return 8212;
                case 152: return 0;
                case 153: return 8482;
                case 154: return 1113;
                case 155: return 8250;
                case 156: return 1114;
                case 157: return 1116;
                case 158: return 1115;
                case 159: return 1119;
                case 160: return 0;
                case 161: return 1038;
                case 162: return 1118;
                case 163: return 1035;
                case 164: return 164;
                case 165: return 1168;
                case 166: return 166;
                case 167: return 167;
                case 168: return 1025;
                case 169: return 169;
                case 170: return 1028;
                case 171: return 171;
                case 172: return 172;
                case 173: return 0;
                case 174: return 174;
                case 175: return 1031;
                case 176: return 176;
                case 177: return 177;
                case 178: return 1030;
                case 179: return 1110;
                case 180: return 1169;
                case 181: return 181;
                case 182: return 182;
                case 183: return 183;
                case 184: return 1105;
                case 185: return 8470;
                case 186: return 1108;
                case 187: return 187;
                case 188: return 1112;
                case 189: return 1029;
                case 190: return 1109;
                case 191: return 1111;
                case 192: return 1040;
                case 193: return 1041;
                case 194: return 1042;
                case 195: return 1043;
                case 196: return 1044;
                case 197: return 1045;
                case 198: return 1046;
                case 199: return 1047;
                case 200: return 1048;
                case 201: return 1049;
                case 202: return 1050;
                case 203: return 1051;
                case 204: return 1052;
                case 205: return 1053;
                case 206: return 1054;
                case 207: return 1055;
                case 208: return 1056;
                case 209: return 1057;
                case 210: return 1058;
                case 211: return 1059;
                case 212: return 1060;
                case 213: return 1061;
                case 214: return 1062;
                case 215: return 1063;
                case 216: return 1064;
                case 217: return 1065;
                case 218: return 1066;
                case 219: return 1067;
                case 220: return 1068;
                case 221: return 1069;
                case 222: return 1070;
                case 223: return 1071;
                case 224: return 1072;
                case 225: return 1073;
                case 226: return 1074;
                case 227: return 1075;
                case 228: return 1076;
                case 229: return 1077;
                case 230: return 1078;
                case 231: return 1079;
                case 232: return 1080;
                case 233: return 1081;
                case 234: return 1082;
                case 235: return 1083;
                case 236: return 1084;
                case 237: return 1085;
                case 238: return 1086;
                case 239: return 1087;
                case 240: return 1088;
                case 241: return 1089;
                case 242: return 1090;
                case 243: return 1091;
                case 244: return 1092;
                case 245: return 1093;
                case 246: return 1094;
                case 247: return 1095;
                case 248: return 1096;
                case 249: return 1097;
                case 250: return 1098;
                case 251: return 1099;
                case 252: return 1100;
                case 253: return 1101;
                case 254: return 1102;
                case 255: return 1103;
                default: return 0;
            }
        }
    }
}