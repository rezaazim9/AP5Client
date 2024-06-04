package Controller;


import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        new Socket("localhost", 1234);
    }
}