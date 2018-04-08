package emulator;

import presenter.IIntraProgramIOUpdateListener;

/**
 * Класс, реализующий систему ввода-вывода эмулятора.
 * @author Maxim Rozhkov
 */
public class IOPeripheralSystem implements IIntraProgramIOActionsListener {

    private IIntraProgramIOUpdateListener intraProgramEventsListener;

    private IScreen screenOnPort0x05;
    private IScreen screenOnPort0x07;

    private Timer timer;
    private Thread timerThread;

    public IOPeripheralSystem(IIntraProgramIOUpdateListener intraProgramEventsListener,
                              IScreen screenOnPort0x05, IScreen characterScreen) {

        this.intraProgramEventsListener = intraProgramEventsListener;
        this.screenOnPort0x05 = screenOnPort0x05;
        this.screenOnPort0x07 = characterScreen;

        this.timer = new Timer();
    }

    @Override
    public void out_0x02(int value) {
        intraProgramEventsListener.consoleOut(value);
    }

    @Override
    public int in_0x08() {
        return intraProgramEventsListener.requestOfInput();
    }

    @Override
    public void out_0x16(int value) {
        timer.setValue(value);
        if (timerThread == null) {
            timerThread = new Thread(timer);
            timerThread.start();
        }
    }

    @Override
    public int in_0x16() {
        return timer.getValue();
    }

    @Override
    public void out_0x05(int value) {
        if(screenOnPort0x05.putByte_Protocol(value)) {
            intraProgramEventsListener.pixelScreenUpdate();
        }
    }

    @Override
    public int in_0x05() {
        return screenOnPort0x05.getByte_Protocol();
    }

    @Override
    public int in_0x07() {
        return screenOnPort0x07.getByte_Protocol();
    }

    @Override
    public void out_0x07(int value) {
        if (screenOnPort0x07.putByte_Protocol(value)) {
            intraProgramEventsListener.characterScreenUpdate();
        }

    }
}