package model.emulator;

import presenter.IIntraProgramIOUpdateEventsListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Класс, реализующий систему ввода-вывода эмулятора.
 * @author Maxim Rozhkov
 */
public class IOPeripheralSystem implements IIntraProgramIOEventsListener {
    private IIntraProgramIOUpdateEventsListener intraProgramIOUpdateListener;

    private IScreen screenOnPort0x05;
    private IScreen screenOnPort0x07;

    private Timer timer;

    private ArrayList<IExternalPeripheral> externalPeripherals;

    public IOPeripheralSystem(IIntraProgramIOUpdateEventsListener intraProgramIOUpdateListener,
                              IScreen screenOnPort0x05, IScreen characterScreen,
                              ArrayList<IExternalPeripheral> externalPeripherals) {

        this.intraProgramIOUpdateListener = intraProgramIOUpdateListener;
        this.screenOnPort0x05 = screenOnPort0x05;
        this.screenOnPort0x07 = characterScreen;

        this.timer = new Timer();
        this.timer.start();

        this.externalPeripherals = externalPeripherals;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(7070);
                    while (true) {
                        Socket socket = serverSocket.accept();
                        IExternalPeripheral externalPeripheral = new ExternalPeripheral(socket, 100 + externalPeripherals.size());
                        externalPeripheral._start();
                        externalPeripherals.add(externalPeripheral);
                    }
                } catch (IOException ignored) {}
            }
        }).start();

        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                for (int i = 0; i < externalPeripherals.size();) {
                    if (!externalPeripherals.get(i).isActive()) {
                        externalPeripherals.remove(externalPeripherals.get(i));
                        System.out.println("удалено!");
                    } else {
                        i++;
                    }
                }
            }
        }, 0, 5000);
    }

    @Override
    public void out(int port, int value) {
        switch (port) {
            case 0x02: {
                intraProgramIOUpdateListener.consoleOut(value);
                break;
            }
            case 0x05: {
                if(screenOnPort0x05.putByte_Protocol(value)) {
                    intraProgramIOUpdateListener.pixelScreenUpdate();
                }
                break;
            }
            case 0x07: {
                if (screenOnPort0x07.putByte_Protocol(value)) {
                    intraProgramIOUpdateListener.characterScreenUpdate();
                }
                break;
            }
            case 0x16: {
                timer.setValue(value);
                break;
            }
            default: {
                for (IExternalPeripheral current : externalPeripherals) {
                    if (current.getPort() == port) {
                        current.write(value);
                    }
                }
            }
        }
    }

    @Override
    public int in(int port) {
        switch (port) {
            case 0x08: {
                return intraProgramIOUpdateListener.requestOfInput();
            }
            case 0x05: {
                return screenOnPort0x05.getByte_Protocol();
            }
            case 0x07: {
                return screenOnPort0x07.getByte_Protocol();
            }
            case 0x16: {
                return timer.getValue();
            }
            default: {
                for (IExternalPeripheral current : externalPeripherals) {
                    if (current.getPort() == port) {
                        return current.read();
                    }
                }
                return 0;
            }
        }
    }
}