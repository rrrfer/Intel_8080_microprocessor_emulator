package kernel;

public class _DByte {
    public static final int MAX_VALUE = 65535;
    public static final int MIN_VALUE = 0;

    public static int getRoundedValue(int value) {
        return (value + 65536) % 65536;
    }
}