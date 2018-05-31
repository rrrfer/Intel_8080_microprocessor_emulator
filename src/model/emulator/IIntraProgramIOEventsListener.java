package model.emulator;

/**
 * Интерфейс слушателя внутрипрограммных командных событий ввода-вывода.
 * Класс, заинтересованный в обработке внутрипрограммных командных событий ввода-вывода,
 * реализует этот интерфейс и объект этого класса передаётся в метод
 * setIntraProgramIOEventsListener(IIntraProgramIOEventsListener ioSystem) класса-микропроцессора,
 * реализующего интерфейс {@link model.kernel.IMicroprocessor}.
 * @author Maxim Rozhkov
 */
public interface IIntraProgramIOEventsListener {
    /**
     * Вызывается, когда исполняемая команда инициирует запись значения в порт
     * @param port номер порта
     * @param value записываемое значение
     */
    void out(int port, int value);

    /**
     * Вызывается, когда исполняемая команда инициирует чтение из порта
     * @param port номер порта
     * @return
     */
    int in(int port);
}