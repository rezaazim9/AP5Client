package Controller;

import Model.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientThreadUpload extends Thread {
    private File file;
    private Account account;
    private int port;
    private static final int MAX_RETRIES = 5;

    public ClientThreadUpload(File file, Account account, int port) {
        this.file = file;
        this.account = account;
        this.port = port;
    }
    @Override
    public void run() {
        DatagramSocket socket = null;
        FileInputStream fileInputStream = null;
        try {
            socket = new DatagramSocket();
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1000];
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = port;
            int bytesRead;
            int sequenceNumber = 0;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, serverAddress, serverPort);
                boolean sent = false;
                int retries = 0;
                while (!sent && retries < MAX_RETRIES) {
                    socket.send(packet);
                    socket.setSoTimeout(1000);
                    byte[] ackBuffer = new byte[10];
                    DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
                    try {
                        socket.receive(ackPacket);
                        String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());
                        if (ack.equals("ACK" + sequenceNumber)) {
                            sent = true;
                        }
                    } catch (IOException e) {
                        retries++;
                    }
                }
                if (!sent) {
                    break;
                }
                sequenceNumber++;
            }
            DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, serverAddress, serverPort);
            socket.send(endPacket);
        } catch (IOException e) {
        }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
    }
}
