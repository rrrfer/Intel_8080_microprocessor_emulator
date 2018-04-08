package emulator;

class PixelScreen implements IScreen {

    private int rowCount;
    private int columnCount;

    private int[][] memory;

    private int outputRegister;
    private int inputState;
    private int rowSelectedAddress;
    private int columnSelectedAddress;

    PixelScreen(int rowCount, int columtCount) {
        this.rowCount = rowCount;
        this.columnCount = columtCount;
        this.memory = new int[rowCount][columtCount];
        this.inputState = 0;
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
                    outputRegister = memory[rowSelectedAddress][columnSelectedAddress];
                    inputState = 0;
                }
                break;
            }
            case 3: {
                memory[rowSelectedAddress][columnSelectedAddress] = value;
                inputState = 0;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getByte_Protocol() {
        return outputRegister;
    }

    @Override
    public int[][] getColorMemory() {
        return memory;
    }

    @Override
    public int[][] getCharMemory() {
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < memory.length; ++i) {
            for (int j = 0; j < memory[i].length; ++j) {
                memory[i][j] = 0;
            }
        }
    }
}
