package kernel;

public interface IReadOnlyMemory {
    int getValueByIndex(int index);
    int getSize();
}
