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
        DatagramPacket packet ;
        DatagramSocket socket;
        try {
            packet = new DatagramPacket(upload,upload.length, InetAddress.getLocalHost(),1234);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
             socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
