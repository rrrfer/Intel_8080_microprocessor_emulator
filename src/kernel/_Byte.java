package kernel;

public class _Byte {
    public static final int MAX_VALUE = 255;
    public static final int MIN_VALUE = 0;

    public static int getRoundedValue(int value) {
        return (value + 256) % 256;
    }
}