package presenter;

import java.io.IOException;

/**
 * Интерфейс контроллера MainView. Класс, управляющий состоянием MainView, реализует этот
 * интерфейс.
 * @author Maxim Rozhkov
 */
public interface IMainPresenter {

    /**
     * Метод для трансляции программы эмулятором.
     * @param program исходный текст программы
     */
    void translation(String program);

    /**
     * Метод для запуска выполнение программы эмулятором.
     */
    void run();

    /**
     * Метод для выполнение одногой команды программы эмулятором.
     */
    void step();

    /**
     * Метод для остановки выполняемой программы эмулятором.
     */
    void stop();

    /**
     * Метод для сброса регистров эмулятора в ноль.
     */
    void resetRegisters();

    /**
     * Метод для сброса памяти эмулятора в ноль.
     */
    void resetMemory();

    /**
     * Метод для установки/удаления точки остановки в программе. Удаление точки остановки
     * проиходит при повторной установки уже существующей точки остановки.
     * @param address адресс точки остановки.
     */
    void setBreakpoint(int address);

    /**
     * Метод для удаления всех точек остановки в программе.
     */
    void removeAllBreakpoints();

    /**
     * Метод для установки программаного счётчика (PC) эмулятора.
     * @param address устанавливаемый адрес программного счётчика.
     */
    void setProgramCounter(int address);

    /**
     * Метод для загрузки программы из файла.
     * @param path путь к файлу.
     * @throws IOException
     */
    void loadProgramFromFile(String path) throws IOException;

    /**
     * Метод для сохранения программы в новый файл.
     * @param path путь к файлу.
     * @param programText текст программы.
     * @throws IOException
     */
    void saveAsProgramInFile(String path, String programText) throws IOException;

    /**
     * Метод для сохранения программы в текущий файл (пересохранение открытого файла).
     * @param programText текст программа.
     * @return true, если файл существует и успешно пересохранён,
     * false, если требуется создание нового файла.
     * @throws IOException
     */
    boolean saveProgramInFile(String programText) throws IOException;
}