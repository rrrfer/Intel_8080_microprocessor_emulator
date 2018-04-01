package emulator;

import presenter.IMainPresenter;

public class IOSystem implements IIOSystem {

    private IMainPresenter presenter;

    public IOSystem(IMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void consoleOut(int value) {
        presenter.consoleOut(value);
    }

    @Override
    public int consoleIn() {
        return presenter.consoleIn();
    }
}