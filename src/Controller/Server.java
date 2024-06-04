package Controller;

import Model.Account;
import Model.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Server {
    public  static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
       ServerSocket serverSocket = new ServerSocket(1234);
       while (true) {
           Socket socket = serverSocket.accept();
           Thread clientThread = new ClientThread(socket);
           clientThread.start();
       }
    }
    public static String hashPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
