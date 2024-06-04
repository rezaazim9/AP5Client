package Model;

import View.LoginMenu;

import java.net.Socket;

public class ClientTCP extends Thread{
    Socket socket;

    public ClientTCP(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        new LoginMenu();
    }
}
