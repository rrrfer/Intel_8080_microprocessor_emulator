package model.kernel;

import model.emulator.IIntraProgramIOEventsListener;

/**
 * Интерфейс слушателя событий исполняемой команды.
 * Класс, заинтересованный в обработке событий, генерируемых выполняемой командой,
 * реализует этот интерфейс, и объект этого класса передаётся в метод
 * execute(IExecutableCommandEventsListener executeListener) класса-команды,
 * реализующего интерфейс {@link model.kernel.cmd.ICommand}.
 * @author Maxim Rozhkov
 */
public interface IExecutableCommandEventsListener {

    /**
     * Вызыватся, когда исполняемая команда отправляет запрос на чтение значения из регистра
     * микропроцессора.
     * @param register регистр, значение которого запрашивает команда.
     * @return значение запрашиваемого регистра.
     */
    int requestOnGetValueFromRegister(Registers register);

    /**
     * Вызывается, когда исполняемая команда отправлет запрос на запись значения в регистр
     * микропроцессора.
     * @param register регистр, в который команда запрашивает запись значения.
     * @param value записываемое значение.
     */
    void requestOnSetValueInRegister(Registers register, int value);

    /**
     * Вызыватся, когда исполняемая команда отправляет запрос на чтение значения из флага
     * микропроцессора.
     * @param flag флаг, значение которого запрашивает команда.
     * @return значение запрашиваемого флага.
     */
    int requestOnGetValueFromFlag(Flags flag);

    /**
     * Вызывается, когда исполняемая команда отправлет запрос на запись значения в флаг
     * микропроцессора.
     * @param flag флаг, в который команда запрашивает запись значения.
     * @param value записываемое значение.
     */
    void requestOnSetValueInFlag(Flags flag, int value);

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на чтение значения из
     * регистровой пары микропроцессора.
     * @param registerPair регистровая пара, значение которой запрашивает команда.
     * @return значение запрашиваемой регистровой пары.
     */
    int requestOnGetValueFromRegisterPair(RegisterPairs registerPair);

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на запись значения в
     * регистровую пару микропроцессора.
     * @param registerPair ригистровая пара, в которую команда запрашивает запись значения.
     * @param value записываемое значение.
     */
    void requestOnSetValueInRegisterPair(RegisterPairs registerPair, int value);

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на чтение значения из регистра
     * флагов микропроцессора.
     * @return значение регистра флагов.
     */
    int requestOnGerValueFromFlagsRegister();

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на запись значения в регистр
     * флагов микропроцессора.
     * @param flags записываемое значение.
     */
    void requestOnSetValueInFlagsRegister(int flags);

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на чтение значения из ячейки
     * памяти микропроцессора.
     * @param address адрес ячейки памяти.
     * @return значение запрашиваемой ячейки памяти.
     */
    int requestOnGetValueFromMemoryByAddress(int address);

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на запись значения в ячеку
     * памяти микропроцессора.
     * @param address адрес ячейки памяти.
     * @param value записываемое значение.
     */
    void requestOnSetValueInMemoryByAddress(int address, int value);

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на подготовку
     * операций ввода-вывода.
     * @return экземпляр класса, реализующий интерфейс
     * {@link IIntraProgramIOEventsListener} или null.
     */
    IIntraProgramIOEventsListener requestOnGetInputOutputActionListener();

    /**
     * Вызывается, когда исполняемая команда отправляет запрос на проверку результата
     * выполнения операции и установку флагов состояния микропроцессора.
     * @param value итоговый результат операции.
     */
    void requestOnCheckByteForSetFlags(int value);
}