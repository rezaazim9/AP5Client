package View;

import Controller.ClientThreadUpload;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class UploadFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton uploadButton;
    JButton backButton;
    JTextArea fileAddress;
    Account account;

    public UploadFrame(Account account) {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        uploadButton = new JButton("Upload");
        backButton = new JButton("Back");
        fileAddress = new JTextArea();
        frame.setBounds(450, 150, 500, 500);
        fileAddress.setBounds(100, 150, 300, 40);
        fileAddress.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));
        panel.add(fileAddress);
        frame.add(panel);
        panel.setLayout(null);
        uploadButton.setBounds(150, 300, 200, 30);
        panel.add(uploadButton);
        backButton.setBounds(150, 400, 200, 30);
        backButton.addActionListener(this);
        uploadButton.addActionListener(this);
        panel.add(backButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenu(account);
        }
        if (e.getSource() == uploadButton) {
            try {
                File file = new File(fileAddress.getText());
                if (!file.exists()) {
                    return;
                }
                Random random = new Random();
                int port = random.nextInt(1000) + 9000;
                Socket socket = new Socket("localhost", 1111);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                AccountFile accountFile = new AccountFile(file.getName(), account, port);
                outputStream.writeObject(new Packet(accountFile, "JWTUpload"));
                outputStream.flush();
                outputStream.close();
                socket.close();
                  new ClientThreadUpload(file, account, port).start();
            } catch (Exception ex) {
            }
        }
    }

}
