package kernel;

public class Memory implements IMemory {

    private int[] memory;

    public Memory(int size) {
        memory = new int[size];
    }

    @Override
    public int getValueByIndex(int index) {
        return memory[index];
    }

    @Override
    public void setValueByIndex(int index, int value) {
        memory[index] = value;
    }

    @Override
    public int[] getAllMemory() {
        return memory;
    }

    @Override
    public int getSize() {
        return memory.length;
    }

    @Override
    public void reset() {
        for (int i = 0; i < memory.length; ++i) {
            memory[i] = 0;
        }
    }
}
