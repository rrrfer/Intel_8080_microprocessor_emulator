package view;

import java.util.ArrayList;

/**
 * Интерфейс view-компонента. Класс, осуществляющий отображение состояния эмулятора, реализует
 * этот интерфейс.
 * @author Maxim Rozhkov
 */
public interface IMainView {

    /**
     * Метод для установки имени открытого файла программы в заголовок окна.
     * @param title имя (полный путь) окрытого файла.
     */
    void setNameEditedFileInTitle(String title);

    /**
     * Метод для установки исходного текста программы в окно редактора кода.
     * @param codeSource исходный текст программы.
     */
    void setProgramText(String codeSource);

    /**
     * Метод для установки позиции отображения программного счётчика.
     * @param programCounterPosition позиция программного счётчика.
     */
    void setProgramCounterPosition(int programCounterPosition, boolean isScroll);

    /**
     * Метод для установки строки статуса трансляции.
     * @param translationResult строка статуса трансляции.
     * @param hasTranslationErrors наличие ошибок в результате трансляции.
     */
    void setTranslationResult(String translationResult, boolean hasTranslationErrors);;

    /**
     * Метод для установки отображения точек остановки.
     * @param breakpointsData список точек остановки.
     */
    void setBreakpoints(int[] breakpointsData);

    /**
     * Метод для уведомления view-компонента об изменении состояний регистров и флагов
     * эмулятора.
     */
    void registersTableUpdate();

    /**
     * Метод для уведомления view-компонента об изменении состояния памяти эмулятора.
     */
    void memoryTableUpdate();

    /**
     * Метод для уведомления view-компонента об изменении таблицы соотношения меток и адресов.
     */
    void label2AddressTableUpdate();

    /**
     * Метод для уведомления view-компонента об изменении данных модели пиксельного экрана.
     */
    void pixelScreenUpdate();

    /**
     * Метод для уведомления view-компонента об изменении данных модели символьного экрана.
     */
    void characterScreenUpdate();

    void externalPeripheralUpdate();

    /**
     * Метод для установки данных в консоль вывода.
     * @param consoleOutData данные для консоли вывода.
     */
    void setConsoleOutputData(String consoleOutData);

    /**
     * Метод для очистки консоли ввода.
     */
    void clearInputConsole();

    /**
     * Метод для установки разрешений действий пользователю. Для различных состояний
     * работы эмулятора существуют различные комбинации доступных и недоступных действий.
     * @param mode режим разрешений дейстий - константа из класса MainPresenter.
     */
    void setPermissionForActions(int mode);

    /**
     * Метод для запроса у view-компонента ввода значения в консоль ввода.
     * @return введёное в консоль ввода значение.
     */
    int requestOfInput();
}