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
    void out(int port, int value);
    int in(int port);
}