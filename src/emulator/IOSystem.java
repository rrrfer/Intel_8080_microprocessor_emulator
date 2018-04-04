package emulator;

import presenter.IMainPresenter;

public class IOSystem implements IIOSystem {

    private IMainPresenter presenter;

    private Timer timer;
    private Thread timerThread;

    public IOSystem(IMainPresenter presenter) {
        this.presenter = presenter;
        this.timer = new Timer();
    }

    @Override
    public void consoleOut(int value) {
        presenter.consoleOut(value);
    }

    @Override
    public int requestOfInput() {
        return presenter.requestOfInput();
    }

    @Override
    public void setTimerValue(int value) {
        timer.setValue(value);
        if (timerThread == null || !timerThread.isAlive()) {
            timerThread = new Thread(timer);
            timerThread.start();
        }
    }

    @Override
    public int getTimerValue() {
        return timer.getValue();
    }
}

class Timer implements Runnable {

    private int value;

    public void setValue(int value) {
        synchronized (this) {
            this.value = value;
        }
    }

    public int getValue() {
        synchronized (this) {
            return value;
        }
    }

    @Override
    public void run() {
        while (value > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
            synchronized (this) {
                value -= 1;
            }
        }
    }
}