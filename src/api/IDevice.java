import java.io.IOException;
import java.net.UnknownHostException;

interface IDevice {
    void connection() throws UnknownHostException, IOException;
    void writeData(int value) throws IOException;
    void sendInterrupt() throws IOException;
}