package kernel;

public interface IMemory extends IReadOnlyMemory {
    void setValueByIndex(int index, int value);
    void reset();
}