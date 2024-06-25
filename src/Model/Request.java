package Model;

import java.io.IOException;
import java.net.Socket;

public class Request {
    public Socket socket;
    public String address;

    public Request(String address) throws IOException {
        this.address = address;
        socket = new Socket("127.0.0.0", 1111);
    }
}
