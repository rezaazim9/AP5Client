package Model;

import java.net.Socket;

public class ClientTCP extends Thread {
    private Socket socket;

    public ClientTCP(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            socket.getOutputStream().write(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
