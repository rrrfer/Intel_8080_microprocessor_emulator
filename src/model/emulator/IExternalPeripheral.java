package model.emulator;

public interface IExternalPeripheral {
    String getDescription();
    int getPort();
    void setPort(int port);
    void write(int value);
    int read();
    void _stop();
    boolean isActive();
    void _start();
}