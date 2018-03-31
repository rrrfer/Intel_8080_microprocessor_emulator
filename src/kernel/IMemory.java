package kernel;

public interface IMemory extends IReadOnlyMemory {
    void setValueByIndex(int index, int value);
    int[] getAllMemory();
    void reset();
}
