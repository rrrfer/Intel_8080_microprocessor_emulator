package emulator;

interface IScreen {
    boolean putByte_Protocol(int value);
    int getByte_Protocol();
    int[][] getMemory();
    void clear();
}
