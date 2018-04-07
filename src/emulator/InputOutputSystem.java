package emulator;

import presenter.IMainPresenter_Model;

public class InputOutputSystem implements IInputOutputSystem {

    private IMainPresenter_Model presenter;

    private IScreen pixelScreen;
    private IScreen charScreen;

    private Timer timer;
    private Thread timerThread;

    public InputOutputSystem(IMainPresenter_Model presenter) {
        this.presenter = presenter;
        this.timer = new Timer();
        this.pixelScreen = new PixelScreen(256, 256);
        this.charScreen = new CharacterScreen(20, 20);

        presenter.setPixelScreenData(pixelScreen.getColorMemory());
        presenter.setCharacterScreenData(charScreen.getColorMemory(), charScreen.getCharMemory());
    }

    @Override
    public void stdOutput(int value) {
        presenter.consoleOut(value);
    }

    @Override
    public int requestOfStdInput() {
        return presenter.requestOfInput();
    }

    @Override
    public void writeTimerValue(int value) {
        timer.setValue(value);
        if (timerThread == null || !timerThread.isAlive()) {
            timerThread = new Thread(timer);
            timerThread.start();
        }
    }

    @Override
    public int readTimerValue() {
        return timer.getValue();
    }

    @Override
    public void writeValueInInputRegisterOfPixelScreen(int value) {
        if(pixelScreen.putByte_Protocol(value)) {
            presenter.pixelScreenUpdate();
        }
    }

    @Override
    public int readValueFromOutputRegisterOfPixelScreen() {
        return pixelScreen.getByte_Protocol();
    }

    @Override
    public void writeValueInInputRegisterOfCharacterScreen(int value) {
        if (charScreen.putByte_Protocol(value)) {
            presenter.characterScreenUpdate();
        }
    }

    @Override
    public int readValueFromOutputRegisterOfCharacterScreen() {
        return charScreen.getByte_Protocol();
    }

    @Override
    public void clearScreens() {
        pixelScreen.clear();
        charScreen.clear();
        presenter.pixelScreenUpdate();
        presenter.characterScreenUpdate();
    }
}