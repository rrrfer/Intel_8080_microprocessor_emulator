package model.emulator;

/**
 * Интерфейс модели экрана.
 * @author Maxim Rozhkov
 */
interface IScreen {

    /**
     * Метод для записи значения в память экрана согласно протоколу записи.
     * @param value записываемое значение.
     * @return true, если видеопамять обновлена.
     */
    boolean putByte_Protocol(int value);

    /**
     * Метод для чтения значение из памяти экрана согласно протоколу чтения.
     * @return читаемое значение.
     */
    int getByte_Protocol();

    /**
     * Метод для получения видеопамяти цвета пикселей экрана.
     * @return двумерный массив видеопамяти цвета пикселей.
     */
    int[][] getColorMemory();

    /**
     * Метод для получения видеопамяти символов экрана.
     * @return двумерный массив видеопамяти символов экрана.
     */
    int[][] getCharMemory();

    /**
     * Метод для очистки экрана.
     */
    void clear();
}
