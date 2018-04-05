package emulator;

public class CharacterScreen implements IScreen {

    private int rowCount;
    private int columnCount;

    private int[][] colorMemory;
    private int[][] charMemory;
    private int inputState;
    private int outputState;
    private int outputRegister;
    private int rowSelectedAddress;
    private int columnSelectedAddress;

    public CharacterScreen(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.colorMemory = new int[rowCount][columnCount];
        this.charMemory = new int[rowCount][columnCount];
        this.inputState = 0;
        this.outputState = 0;
        this.outputRegister = 0;
    }

    @Override
    public boolean putByte_Protocol(int value) {
        switch (inputState) {
            case 0: {
                if (value < rowCount) {
                    rowSelectedAddress = value;
                    inputState = 1;
                    outputRegister = 0x00;
                } else {
                    outputRegister = 0xFF;
                }
                break;
            }
            case 1: {
                if (value < columnCount) {
                    columnSelectedAddress = value;
                    inputState = 2;
                    outputRegister = 0x00;
                } else {
                    outputRegister = 0xFF;
                }
                break;
            }
            case 2: {
                outputRegister = 0x00;
                if (value == 0) {
                    inputState = 3;
                } else {
                    outputRegister = colorMemory[rowSelectedAddress][columnSelectedAddress];
                    inputState = 0;
                }
                break;
            }
            case 3: {
                colorMemory[rowSelectedAddress][columnSelectedAddress] = value;
                outputRegister = 0x00;
                inputState = 4;
                break;
            }
            case 4: {
                charMemory[rowSelectedAddress][columnSelectedAddress] = value;
                outputRegister = 0x00;
                inputState = 0;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getByte_Protocol() {
        int returnValue = outputRegister;
        switch (outputState) {
            case 0: {
                outputState = 1;
                outputRegister = charMemory[rowSelectedAddress][columnSelectedAddress];
                break;
            }
            case 1: {
                outputState = 0;
                outputRegister = colorMemory[rowSelectedAddress][columnSelectedAddress];
                break;
            }
        }
        return returnValue;
    }

    @Override
    public int[][] getColorMemory() {
        return colorMemory;
    }

    @Override
    public int[][] getCharMemory() {
        return charMemory;
    }

    @Override
    public void clear() {
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                charMemory[i][j] = 0;
                colorMemory[i][j] = 0;
            }
        }
    }
}