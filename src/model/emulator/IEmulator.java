package model.emulator;

import model.kernel.Flags;
import model.kernel.Registers;
import presenter.IIntraProgramIOUpdateEventsListener;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Интерфейс программного эмулятора микропроцессора Intel 8080. Класс, описывающий программный
 * эмулятор микропроцессора Intel 8080, реализует этот интерфейс.
 * @author Maxim Rozhkov
 */
public interface IEmulator {

    /**
     * Метод для трансляции и последующей загрузки программы в память микропроцессора.
     * @param program исходный текст программы.
     */
    void translation(String program);

    /**
     * Метод для запуска выполнения программы.
     */
    void run();

    /**
     * Метод для выполнения текущей команды микропроцессора.
     * @return true, если конец программы, иначе false.
     */
    boolean step();

    /**
     * Метод для чтения значения из выбранного регистра микропроцессора.
     * @param register регистр микропроцессора.
     * @return значение из выбранного регистра.
     */
    int getValueFromRegister(Registers register);

    /**
     * Метод для чтения значения из выбранной ячейки памяти микропроцессора.
     * @param address адрес ячейки памяти.
     * @return значение из выбранной ячейки памяти.
     */
    int getValueFromMemoryByAddress(int address);

    /**
     * Метод для чтения значения из выбранного флага состояния микропроцессора.
     * @param flag флаг микропроцессора.
     * @return значение из выбранного флага микропроцессора.
     */
    int getValueFromFlag(Flags flag);

    /**
     * Метод для получения размера памяти микропроцессора.
     * @return размер памяти микропроцессора.
     */
    int getMemorySize();

    /**
     * Метод для сброса регистров микропроцессора в нулевое состояние.
     */
    void resetRegisters();

    /**
     * Метод для сброса памяти микропроцессора в нулевое состояние.
     */
    void resetMemory();

    /**
     * Метод для очистки экранов эмулятора.
     */
    void clearScreen();

    /**
     * Метод для установки значения программного счётчика (регистра PC).
     * @param address устанавливаемое значение програмного счётчика.
     */
    void setProgramCounter(int address);

    /**
     * Метод для установки точки остановки.
     * @param address адрес точки остановки.
     */
    void setBreakpoint(int address);

    /**
     * Метод для удаления всех точек остановки.
     */
    void removeAllBreakpoints();

    /**
     * Метод для получения списка всех точек остановки.
     * @return список точек остановки в программе.
     */
    int[] getBreakpoints();

    /**
     * Метод для получения строки с уведомлением о результате трансляции.
     * @return строка, с результатом трансляции, содержащим перечисление ошибок.
     */
    String getErrors();

    /**
     * Метод для получения списка полных имён команд микропроцессора, находящихся в памяти
     * после успешной трансляции программы.
     * @return массив полных имён команд микропроцессора.
     */
    String[] getCommandsList();

    /**
     * Метод для получения видеопамяти пиксельного экрана эмулятора.
     * @return двумерный массив содержащий коды цвета пиксельного экрана эмулятора.
     */
    int[][] getPixelScreenMemory();

    /**
     * Метод для получения видеопамяти цветов символьного экрана эмулятора.
     * @return двумерный массив содержащий коды цвета пикселей и символов
     * символьного экрана эмулятора.
     */
    int[][] getCharacterScreenColorMemory();

    /**
     * Метод для получения видеопамяти символов символьного экрана эмулятора.
     * @return двумерный массив содержащий коды символов символьного экрана эмулятора.
     */
    int[][] getCharacterScreenCharMemory();

    /**
     * Метод для загрузки текста прогрммы из файла.
     * @param path путь к файлу.
     * @return текст программы.
     * @throws IOException
     */
    String loadProgramFromFile(String path) throws IOException;

    /**
     * Метод для сохранения текста программы в файл.
     * @param path путь к файлу.
     * @param programText текст программы.
     * @throws IOException
     */
    void saveProgramInFile(String path, String programText) throws IOException;

    /**
     * Метод для установки сушателя действий ввода-вывода выполняемой эмулятором программы.
     * @param actionsListener экземпляр класса, реализующий интрфейс
     * {@link IIntraProgramIOUpdateEventsListener}.
     */
    void setIntraProgramIOUpdateEventsListener(IIntraProgramIOUpdateEventsListener actionsListener);

    /**
     * @return true, при наличии синтаксических ошибок в коде программы, иначе false.
     */
    boolean hasTranslationErrors();

    /**
     * @return список пар значений: метка - адрес.
     */
    ArrayList<String> getLabel2AddressList();

    ArrayList<IExternalPeripheral> getExternalPeripheral();
}
