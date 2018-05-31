import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Интерфейс периферийного устройства
 */
interface IDevice {
    /**
     * Данный метод выполняет подключение к эмулятору микропроцессора Intel 8080
     * и запускает поток информационного обмена
     * @throws UnknownHostException
     * @throws IOException
     */
    void connection() throws UnknownHostException, IOException;

    /**
     * Метод для записи значения в выходной порт устройства
     * @param value записываемое значение
     * @throws IOException
     */
    void writeData(int value) throws IOException;

    /**
     * Метод для отправки сигнала прерывания микропроцессору
     * @throws IOException
     */
    void sendInterrupt() throws IOException;
}