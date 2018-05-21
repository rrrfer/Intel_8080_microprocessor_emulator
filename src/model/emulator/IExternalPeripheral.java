package model.emulator;

public interface IExternalPeripheral {
    String getDescription();
    int getPort();
    void setPort(int port);
    void write(int value);
    int read();
    boolean isActive();
    void _start();
    boolean _isInterrupted();
    void _setPriority(int priority);
    int _getPriority();
}