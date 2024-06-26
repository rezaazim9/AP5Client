package View;

import Model.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MainMenu implements ActionListener {
    JFrame frame;
    JButton exitButton;
    JPanel panel;
    JButton downloadButton;
    JButton uploadButton;
    JButton viewAccessButton;
    JButton viewButton;
    JButton requestAccessButton;
    Account account;
    Socket socket;

    public MainMenu(Account account,Socket socket){
        this.socket = socket;
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        exitButton = new JButton("Exit");
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        downloadButton = new JButton("Download");
        panel.add(downloadButton);
        downloadButton.setBounds(150, 100, 200, 30);
        viewAccessButton = new JButton("View Requested Access");
        panel.add(viewAccessButton);
        viewAccessButton.setBounds(150, 150, 200, 30);
        uploadButton = new JButton("Upload");
        panel.add(uploadButton);
        uploadButton.setBounds(150, 250, 200, 30);
        viewButton = new JButton("View");
        panel.add(viewButton);
        viewButton.setBounds(150, 300, 200, 30);
        requestAccessButton = new JButton("Request Access");
        panel.add(requestAccessButton);
        requestAccessButton.setBounds(150, 200, 200, 30);
        panel.add(exitButton);
        exitButton.setBounds(150, 350, 200, 30);
        exitButton.addActionListener(this);
        downloadButton.addActionListener(this);
        viewAccessButton.addActionListener(this);
        uploadButton.addActionListener(this);
        viewButton.addActionListener(this);
        requestAccessButton.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            frame.dispose();
            new LoginMenu();
        }
        if (e.getSource() == downloadButton) {
            frame.dispose();
            new DownloadFrame(account);
        }
        if (e.getSource() == viewAccessButton) {
            frame.dispose();
            new ViewAccessFrame(account);
        }
        if (e.getSource() == uploadButton) {
            frame.dispose();
            new UploadFrame(account);
        }
        if (e.getSource() == viewButton) {
            frame.dispose();
            new ViewFrame(account);
        }
        if (e.getSource() == requestAccessButton) {
            frame.dispose();
            new RequestAccessFrame(account);
        }
    }
}
