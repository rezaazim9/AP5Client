package View;

import Model.Account;
import Model.ClientThreadUpload;
import Model.JWTThread;
import Model.RFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UploadFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton uploadButton;
    JButton backButton;
    JTextArea fileAddress;
    Account account;
    static int id = 0;

    public UploadFrame(Account account) {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        uploadButton = new JButton("Upload");
        backButton = new JButton("Back");
        fileAddress = new JTextArea();
        frame.setBounds(450, 150, 500, 500);
        fileAddress.setBounds(100, 150, 300, 30);
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
            JWTThread jwtThread = new JWTThread(account.jwt.getToken(), frame);
            jwtThread.start();
            if (jwtThread.getAccess()) {
                File file = new File(fileAddress.getText());
                account.files.add(new RFile(id++, file));
                long length = file.length();
                while (length > 0) {
                    length -= 1000;
                    byte[] upload = new byte[1000];
                    if (length < 0) {
                        upload = new byte[(int) (length + 1000)];
                    }
                    ClientThreadUpload clientThreadUpload = new ClientThreadUpload(file, account, upload);
                    clientThreadUpload.start();
                }
            }
        }
    }
}
