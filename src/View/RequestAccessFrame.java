package View;

import Model.Account;
import Model.Packet;
import Model.RequestAccess;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestAccessFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton backButton;
    Account account;
    JTextArea fileName;
    JButton accessButton;

    public RequestAccessFrame(Account account) {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        accessButton = new JButton("Request Access");
        accessButton.setBounds(150, 300, 200, 30);
        fileName = new JTextArea();
        fileName.setText("Enter the file name");
        fileName.setBounds(150, 200, 200, 30);
        panel.add(fileName);
        backButton = new JButton("Back");
        backButton.setBounds(150, 400, 200, 30);
        backButton.addActionListener(this);
        accessButton.addActionListener(this);
        panel.add(backButton);
        panel.add(accessButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenu(account);
        }
        if (e.getSource() == accessButton) {
            Socket socket;
            try {
                socket = new Socket("localhost", 1111);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(new Packet(new RequestAccess(account,fileName.getText()), "requestAccess"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
