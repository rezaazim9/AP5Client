package Model;

import java.io.File;
import java.io.IOException;
import java.net.*;

public class ClientThreadUpload extends Thread {
    File file;
    Account account;
    byte[] upload;


    public ClientThreadUpload(File file, Account account, byte[] upload) {
        this.file = file;
        this.account = account;
        this.upload = upload;
    }

    @Override
    public void run() {
        DatagramPacket packet;
        try {
            new Socket("localhost", 1111);
            packet = new DatagramPacket(upload, upload.length, InetAddress.getLocalHost(), 2222);
            DatagramSocket socket1 = new DatagramSocket();
            socket1.send(packet);
        } catch (IOException e) {
        }
    }
}
