package View;

import Model.Account;
import Model.Packet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DownloadFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton downloadButton;
    JButton backButton;
    Account account;

    public DownloadFrame(Account account) {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        downloadButton = new JButton("Download");
        backButton = new JButton("Back");
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        downloadButton.setBounds(150, 100, 200, 30);
        panel.add(downloadButton);
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
                Socket socket = new Socket("localhost", 1111);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(new Packet(account, "JWTDownload"));
            } catch (Exception ex) {

            }
        }
    }
}
