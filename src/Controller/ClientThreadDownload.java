package Controller;

import Model.AccountFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientThreadDownload extends Thread {
    private AccountFile accountFile;
    private File file;
    private int port;

    public ClientThreadDownload(AccountFile accountFile, File file, int port) {
        this.accountFile = accountFile;
        this.file = file;
        this.port = port;
    }

    @Override
    public void run() {
        DatagramSocket socket = null;
        FileOutputStream fileOutputStream = null;
        try {
            byte[] buffer = new byte[1000];
            socket = new DatagramSocket(port);
            fileOutputStream = new FileOutputStream(file);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            int expectedSequenceNumber = 0;
            while (true) {
                socket.receive(packet);
                if (packet.getLength() == 0) {
                    break;
                }
                fileOutputStream.write(packet.getData(), 0, packet.getLength());
                fileOutputStream.flush();
                String ack = "ACK" + expectedSequenceNumber;
                DatagramPacket ackPacket = new DatagramPacket(ack.getBytes(), ack.length(), packet.getAddress(), packet.getPort());
                socket.send(ackPacket);
                expectedSequenceNumber++;
            }
        } catch (IOException e) {
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
