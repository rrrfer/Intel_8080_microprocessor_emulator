package kernel;

public interface IMemory {
    int getValueByIndex(int index);
    void setValueByIndex(int index, int value);
    int[] getAllMemory();
    void reset();
    int getSize();
}
