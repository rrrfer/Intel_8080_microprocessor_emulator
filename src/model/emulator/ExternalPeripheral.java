package model.emulator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ExternalPeripheral extends Thread implements IExternalPeripheral {

    private String description;
    private int port;

    private DataInputStream input;
    private DataOutputStream output;

    private int register;
    private boolean interrupt;
    private int priority;

    public ExternalPeripheral(Socket socket, int port) throws IOException {
        this.port = port;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        description = input.readUTF();
        interrupt = false;
        priority = 8;
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
    public boolean _isInterrupted() {
        boolean state = interrupt;
        interrupt = false;
        return state;
    }

    @Override
    public void _setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int _getPriority() {
        return priority;
    }

    @Override
    public void run() {
        while(true) {
            try {
                int inputData = input.readInt();
                if (inputData < 256) {
                    synchronized (this) {
                        register = inputData;
                    }
                } else {
                    synchronized (this) {
                        interrupt = true;
                    }
                }
            } catch (IOException e) {
                currentThread().stop();
            }
        }
    }
}