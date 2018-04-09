package model.emulator;

import presenter.IIntraProgramIOUpdateEventsListener;

/**
 * Класс, реализующий систему ввода-вывода эмулятора.
 * @author Maxim Rozhkov
 */
public class IOPeripheralSystem implements IIntraProgramIOActionsListener {
    private IIntraProgramIOUpdateEventsListener intraProgramIOUpdateListener;

    private IScreen screenOnPort0x05;
    private IScreen screenOnPort0x07;

    private Timer timer;
    private Thread timerThread;
    public IOPeripheralSystem(IIntraProgramIOUpdateEventsListener intraProgramIOUpdateListener,
                              IScreen screenOnPort0x05, IScreen characterScreen) {

        this.intraProgramIOUpdateListener = intraProgramIOUpdateListener;
        this.screenOnPort0x05 = screenOnPort0x05;
        this.screenOnPort0x07 = characterScreen;

        this.timer = new Timer();
    }

    @Override
    public void out_0x02(int value) {
        intraProgramIOUpdateListener.consoleOut(value);
    }

    @Override
    public int in_0x08() {
        return intraProgramIOUpdateListener.requestOfInput();
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
            intraProgramIOUpdateListener.pixelScreenUpdate();
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
            intraProgramIOUpdateListener.characterScreenUpdate();
        }
    }
}