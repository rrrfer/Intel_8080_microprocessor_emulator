package kernel;

public class _DByte {
    public static int getRoundedValue(int value) {
        return (value + 65536) % 65536;
    }
}