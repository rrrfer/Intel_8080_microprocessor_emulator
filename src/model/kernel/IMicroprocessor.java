package model.kernel;

import model.emulator.IIntraProgramIOEventsListener;
import model.kernel.cmd.ICommand;

/**
 * Интерфейс микропроцессора. Класс, описывающий модель внутреннего состояния
 * микропроцессора, реализует этот интерфейс.
 * @author Maxim Rozhkov
 */
public interface IMicroprocessor {

    /**
     * Метод для чтения значения из выбранного регистра микропроцессора.
     * @param register выбранный регистр.
     * @return значение из выбранного регистра микропроцессора.
     */
    int getValueFromRegister(Registers register);

    /**
     * Метод для записи значения в выбранный регистр микропроцессора.
     * @param register выбранный регистр.
     * @param value записываемое значение.
     */
    void setValueInRegister(Registers register, int value);

    /**
     * Метод для чтения значения из выбранной регистровой пары микропроцессора.
     * @param registerPair выбранная регистровая пара.
     * @return значение из выбранной регистровой пары микропроцессора.
     */
    int getValueFromRegisterPair(RegisterPairs registerPair);

    /**
     * Метод для записи значения в выбранную регистровую пару.
     * @param registerPair выбранная регистровая пара.
     * @param value записываемое значение.
     */
    void setValueInRegisterPair(RegisterPairs registerPair, int value);

    /**
     * Метод для чтения значения из выбранного флага состояния микропроцессора.
     * @param flag выбранный флаг состояния.
     * @return значение из выбранного флага состояния микропроцессора.
     */
    int getValueFromFlag(Flags flag);

    /**
     * Метод для записи значения в выбранный флаг состояния микропроцессора.
     * @param flag выбранный флаг состояния.
     * @param value записываемое значение.
     */
    void setValueInFlag(Flags flag, int value);

    /**
     * Метод для чтения значения из регистра флагов микропроцессора.
     * Для чтения значения из конкретного флага используйте метод
     * getValueFromFlag(Flags flag).
     * Регистр флагов недоступен для прямой модификации ни одной из команд микропроцессора
     * Intel 8080. Регистр флагов доступен в составе регистровой пары PSW (A + Flags Register)
     * для команд POP и PUSH. Данный метод используется в реализации этих команд.
     * @return значение регистра флагов микропроцессора.
     */
    int getAllFlags();

    /**
     * Метод для записи значения в регист флагов микропроцессора.
     * Для записи значения в конкретный флаг используйте метод
     * setValueInFlag(Flags flag, int value).
     * Регистр флагов недоступен для прямой модификации ни одной из команд микропроцессора
     * Intel 8080. Регистр флагов доступен в составе регистровой пары PSW (A + Flags Register)
     * для команд POP и PUSH. Данный метод используется в реализации этих команд.
     * @param flags записываемое значение.
     */
    void setAllFlags(int flags);

    /**
     * Метод для получения экземпляра класса, реализующего интерфейс {@link IIntraProgramIOEventsListener},
     * используемого микропроцессором.
     * @return возвращает экземпляр класса, реализующего интерфейс {@link IIntraProgramIOEventsListener},
     * используемого микропроцессором.
     */
    IIntraProgramIOEventsListener getIntraProgramIOEventsListener();

    /**
     * Метод для установки экземпляра класса, реализующего интерфейс {@link IIntraProgramIOEventsListener},
     * в микропроцессор.
     * @param ioSystem класс, реализующий интерфейс {@link IIntraProgramIOEventsListener}.
     */
    void setIntraProgramIOEventsListener(IIntraProgramIOEventsListener ioSystem);

    /**
     * Метод для выполнения команды микропроцессором.
     * @param command выполняемая команда. Класс, реализующий интерфейс {@link model.kernel.cmd.ICommand}.
     */
    void executeCommand(ICommand command);

    /**
     * Метод для проверки результата выполнения команды и
     * установки/сброса флагов состояния микропроцессора.
     * @param value проверяемое значение.
     */
    void checkValueForSetFlags(int value);

    /**
     * Метод для сброса регистров микропроцессора в нулевое состояние.
     */
    void resetRegisters();

    /**
     * Метод для сброса памяти микропроцессора в нулевое состояние.
     */
    void resetMemory();

    /**
     * Метод для чтения значения из памяти по указанному адресу.
     * @param address адрес ячейки памяти.
     * @return значение из указанной ячейки памяти.
     */
    int getValueFromMemoryByAddress(int address);

    /**
     * Метод для записи значения в память по указанному адресу.
     * @param address адрес ячейки памяти.
     * @param value записываемое значение.
     */
    void setValueInMemoryByAddress(int address, int value);

    /**
     * Метод для получения размера памяти.
     * @return размер памяти.
     */
    int getMemorySize();

    /**
     * Метод для установки уровня приоритета исполняеммой программы.
     * Основная программа выполняется с приоритетом 8.
     * Приоритет повышается при входе в режим обработки прерывания и зависит от
     * приоритета самого прерывания (7, 6, 5, 4, 3, 2, 1, 0)
     * @param level приоритет
     */
    void setExecutionLevel(int level);

    /**
     * Метод для получения текущего приоритета исполняемой программы
     * @return уровень приоритета 0 - наивысший 8 - основная программа
     */
    int getExecutionLevel();

    /**
     * Метод для возврата к предыдущему уровня приоритета исполнения программы
     */
    void returnFromInterrupt();

    /**
     * Переход в прерывание
     * @param command
     */
    void interrupt(ICommand command);
}