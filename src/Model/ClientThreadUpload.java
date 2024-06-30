package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.util.Random;

public class ClientThreadUpload extends Thread {
    File file;
    Account account;
    byte[] upload;
    int port;


    public ClientThreadUpload(File file, Account account, byte[] upload, int port) {
        this.file = file;
        this.account = account;
        this.upload = upload;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1000];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket(port);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (fileInputStream.read(upload) != -1) {
                packet.setData(upload);
                packet.setLength(upload.length);
                packet.setAddress(InetAddress.getByName("localhost"));
                packet.setPort(2222);
            }
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
        }
    }
}
