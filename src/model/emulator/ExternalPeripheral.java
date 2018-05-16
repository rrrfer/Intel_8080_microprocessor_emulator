package model.emulator;

import model.kernel._Byte;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ExternalPeripheral extends Thread implements IExternalPeripheral {

    private String description;
    private int port;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private int register;

    public ExternalPeripheral(Socket socket, int port) throws IOException {
        this.socket = socket;
        this.port = port;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        description = input.readUTF();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void write(int value) {
        try {
            output.writeInt(value);
        } catch (IOException ignored) {}
    }

    @Override
    public int read() {
        return register;
    }

    @Override
    public void _stop() {
        try {
            socket.close();
            this.stop();
        } catch (IOException ignored) {}
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean isActive() {
        return this.isAlive();
    }

    @Override
    public void _start() {
        this.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                int inputData = input.readInt();
                synchronized (this) {
                    register = _Byte.getRoundedValue(inputData);
                }
            } catch (IOException ignored) {
                currentThread().stop();
            }
        }
    }
}