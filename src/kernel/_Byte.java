package kernel;

public class _Byte {
    public static int getRoundedValue(int value) {
        return (value + 256) % 256;
    }
}