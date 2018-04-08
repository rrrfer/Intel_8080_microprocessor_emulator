package kernel.cmd;

import kernel.ICommandsExecuteListener;

/**
 * Интерфейс команды микропроцессора. Класс, описывающий команду микропроцессора,
 * реализует этот интерфейс.
 * @author Maxim Rozhkov
 */
public interface ICommand {

    /**
     * Метод, в котором описывает логика выполнения команды. Вызов данного метода приводит к
     * выполнению команды.
     * @param executeListener экземпляр класса {@link kernel.ICommandsExecuteListener},
     * слушающий действия исполняемой команды.
     */
    void execute(ICommandsExecuteListener executeListener);

    /**
     * Метод для получения размера команды в байтах.
     * @return размер команды в байтах.
     */
    int getSize();

    /**
     * Метод для получения полного имени команды (MVI A, 0xFF).
     * @return строка с полным именем команды.
     */
    String getName();
}
