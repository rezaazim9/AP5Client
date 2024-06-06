package View;

import Model.Account;
import Model.RFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton backButton;
    JList<String> list;
    Account account;

    public ViewFrame(Account account) {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        ArrayList<String> files = new ArrayList<>();
        for (RFile file : account.getFiles()) {
            files.add(file.file.getName());
        }
        list = new JList<>(files.toArray(new String[0]));
        list.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 25));
        list.setBounds(150, 20, 200, 330);
        panel.add(list);
        backButton = new JButton("Back");
        backButton.setBounds(150, 400, 200, 30);
        backButton.addActionListener(this);
        panel.add(backButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenu(account);
        }
    }
}
