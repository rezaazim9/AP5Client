package View;

import Model.Account;
import Model.Packet;
import Model.RFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ViewFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton backButton;
    JList<String> list;
    Account account;

    public ViewFrame(Account account) throws IOException, ClassNotFoundException {

        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        Socket socket = new Socket("localhost", 1111);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream.writeObject(new Packet(account, "view"));
        ArrayList<String> filesName=(ArrayList<String>) inputStream.readObject();
        list = new JList<>(filesName.toArray(new String[0]));
        list.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 25));
        list.setBounds(150, 20, 200, 330);
        panel.add(list);
        backButton = new JButton("Back");
        backButton.setBounds(150, 400, 200, 30);
        backButton.addActionListener(this);
        panel.add(backButton);
        frame.setVisible(true);
        socket.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenu(account);
        }
    }
}
