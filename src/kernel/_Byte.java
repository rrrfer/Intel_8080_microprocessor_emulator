package kernel;

/**
 * Класс, предоставляющий статический метод, для приведения значения в интервал байта.
 * @author Maxim Rozhkov
 */
public class _Byte {

    /**
     * Метод для обработки переполнения байта.
     * @param value значение.
     * @return значение, приведённое в рамки байта (257 : 1, 258 : 2, -3 : 253).
     */
    public static int getRoundedValue(int value) {
        return (value + 256) % 256;
    }
}