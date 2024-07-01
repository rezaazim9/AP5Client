package View;

import Model.Account;
import Model.AccountFile;
import Controller.ClientThreadDownload;
import Model.Packet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class DownloadFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton downloadButton;
    JButton backButton;
    Account account;
    JTextArea fileAddress;

    public DownloadFrame(Account account) {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        downloadButton = new JButton("Download");
        backButton = new JButton("Back");
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        downloadButton.setBounds(150, 300, 200, 30);
        panel.add(downloadButton);
        fileAddress = new JTextArea();
        fileAddress.setBounds(100, 150, 300, 40);
        fileAddress.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));
        panel.add(fileAddress);
        backButton.setBounds(150, 400, 200, 30);
        panel.add(backButton);
        backButton.addActionListener(this);
        downloadButton.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenu(account);
        }
        if (e.getSource() == downloadButton) {
            try {
                String fileName = fileAddress.getText();
                Random random = new Random();
                int port = random.nextInt(1000) + 9000;
                Socket socket = new Socket("localhost", 1111);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                AccountFile accountFile = new AccountFile(fileName, account, port);
                outputStream.writeObject(new Packet(accountFile, "JWTDownload"));
                outputStream.flush();
                outputStream.close();
                socket.close();
                File file = new File("C:\\Users\\ostad\\Downloads" + File.separator + fileName);
                new ClientThreadDownload(accountFile, file, port).start();
            } catch (Exception ex) {
            }
        }

    }
}
