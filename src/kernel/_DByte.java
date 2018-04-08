package kernel;

/**
 * Класс, предоставляющий статический метод, для приведения значения в интервал слова
 * (двойного байта).
 * @author Maxim Rozhkov
 */
public class _DByte {

    /**
     * Метод для обработки переполнения слова (двойного байта).
     * @param value значение.
     * @return значение, приведённое в рамки байта (65537 : 1, 65538 : 2, -3 : 65533).
     */
    public static int getRoundedValue(int value) {
        return (value + 65536) % 65536;
    }
}