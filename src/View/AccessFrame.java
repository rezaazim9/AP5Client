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

public class AccessFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton backButton;
    JButton addButton;
    JButton removeButton;
    JTextArea accountName;
    JList<String> requestList;
    Account account;
    JList<String> list;
    RFile rfile = new RFile(0, null, new ArrayList<>(), new ArrayList<>());

    public AccessFrame(Account account, int id) throws IOException, ClassNotFoundException {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        Socket socket = new Socket("localhost", 1111);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream.writeObject(new Packet(id, "viewRequests"));
        ArrayList<String> requests= (ArrayList<String>) inputStream.readObject();
        list.setBounds(250, 20, 150, 200);
        requestList = new JList<>(requests.toArray(new String[0]));
        requestList.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 25));
        requestList.setBounds(50, 20, 150, 200);
        list.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));
        panel.add(requestList);
        panel.add(list);
        panel.setLayout(null);
        accountName = new JTextArea();
        accountName.setText("Enter the account name");
        accountName.setBounds(150, 250, 200, 30);
        panel.add(accountName);
        addButton = new JButton("Add");
        addButton.setBounds(150, 300, 200, 30);
        panel.add(addButton);
        addButton.addActionListener(this);
        removeButton = new JButton("Remove");
        removeButton.setBounds(150, 350, 200, 30);
        panel.add(removeButton);
        removeButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.setBounds(150, 400, 200, 30);
        panel.add(backButton);
        backButton.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new ViewAccessFrame(account);
        }
        if (e.getSource() == addButton) {
//            for (Account account1:Server.accounts){
//                if (account1.getName().equals(accountName.getText())){
//                    rfile.addAccount(account1);
//                    account1.getFiles().add(rfile);
//                    frame.dispose();
//                    rfile.removeRequest(account1);
//                    new AccessFrame(account, rfile.file.getName());
//                }
//            }
        }
        if (e.getSource() == removeButton) {
//            for (Account account1:Server.accounts){
//                if (account1.getName().equals(accountName.getText())){
//                    rfile.removeAccount(account1);
//                    account1.getFiles().remove(rfile);
//                    frame.dispose();
//                    rfile.removeRequest(account1);
//                    new AccessFrame(account, rfile.file.getName());
//                }
//            }
        }
    }
}
