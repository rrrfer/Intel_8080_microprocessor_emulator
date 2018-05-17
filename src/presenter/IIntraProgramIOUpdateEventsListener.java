package presenter;

/**
 * Интерфейс слушателя внутрипрограммных событий обновлений устройств ввода-вывода
 * (функционал, необходимый для управления view компонентами из программы, выполняющейся на эмуляторе).
 * Класс, заинтересованный в обработке внутрипрограммных событий обновлений устройств ввода-вывода,
 * реализует этот интерфейс и объект этого класса передаётся в метод
 * setIntraProgramIOUpdateEventsListener(IIntraProgramIOUpdateEventsListener actionsListener) класса-эмулятора,
 * реализующего интерфейс {@link model.emulator.IEmulator}.
 * @author Maxim Rozhkov
 */
public interface IIntraProgramIOUpdateEventsListener {

    /**
     * Метод вызывается, когда исполняемая эмулятором программа инициирует вывод данных в консоль
     * вывода.
     * @param value выводимое значение.
     */
    void consoleOut(int value);

    /**
     * Метод вызывается, когда исполняемая эмулятором программа инициирует запрос ввода данных
     * из консоли ввода.
     * @return введёное в консоль ввода значение.
     */
    int requestOfInput();

    /**
     * Метод вызывается, когда видеопамять пиксельного экрана модифицируется.
     */
    void pixelScreenUpdate();

    /**
     * Метод вызывается, когда видеопамять символьного экрана модифицируется.
     */
    void characterScreenUpdate();

    void externalPeripheralUpdate();
}