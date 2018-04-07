package presenter;

public interface IMainPresenter_Model {
    void consoleOut(int value);
    int requestOfInput();
    void setPixelScreenData(int[][] memory);
    void setCharacterScreenData(int[][] colorMemory, int[][] charMemory);
    void pixelScreenUpdate();
    void characterScreenUpdate();
}