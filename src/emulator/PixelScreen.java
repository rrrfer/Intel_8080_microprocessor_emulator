package emulator;

class PixelScreen implements IScreen {

    private int height;
    private int weight;

    private int[][] memory;

    private int outputRegister;
    private int byteCounter;
    private int rowAddress;
    private int columnAddress;

    public PixelScreen(int height, int weight) {
        this.height = height;
        this.weight = weight;
        this.memory = new int[height][weight];
        this.byteCounter = 0;
        this.outputRegister = 0;
    }

    @Override
    public boolean putByte_Protocol(int value) {
        switch (byteCounter) {
            case 0: {
                if (value < height) {
                    rowAddress = value;
                    byteCounter = 1;
                    outputRegister = 0x00;
                } else {
                    outputRegister = 0xFF;
                }
                break;
            }
            case 1: {
                if (value < weight) {
                    columnAddress = value;
                    byteCounter = 2;
                    outputRegister = 0x00;
                } else {
                    outputRegister = 0xFF;
                }
                break;
            }
            case 2: {
                if (value == 0) {
                    byteCounter = 3;
                    outputRegister = 0x00;
                } else {
                    outputRegister = memory[rowAddress][columnAddress];
                    byteCounter = 0;
                }
                break;
            }
            case 3: {
                memory[rowAddress][columnAddress] = value;
                byteCounter = 0;
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
    public int[][] getMemory() {
        return memory;
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
