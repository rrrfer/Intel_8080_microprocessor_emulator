import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class Device extends Thread implements IDevice {

    private String description;

    private DataInputStream input;
    private DataOutputStream output;

    public Device(String description) {
        this.description = description;
    }

    @Override
    final public void connection() throws UnknownHostException, IOException {
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inetAddress, 7070);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF(description);
        this.start();
    }

    @Override
    final public void writeData(int value) throws IOException {
        synchronized (this) {
            output.writeInt((value + 256) % 256);
        }
    }

    @Override
    final public void sendInterrupt() throws IOException {
        synchronized (this) {
            output.writeInt(Integer.MAX_VALUE);
        }
    }

    @Override
    final public void run() {
        while (true) {
            try {
                int data = input.readInt();
                if (data >= 0 && data < 256) {
                    receivingData(data);
                } else {
                    readACK();
                }
            } catch (IOException ignored) {
                break;
            }
        }
    }

    protected void readACK() {}

    protected void receivingData(int value) {}
}