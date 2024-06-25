package View;

import Model.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAccessFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton backButton;
    Account account;
    JTextArea file;
    JButton accessButton;

    public ViewAccessFrame(Account account) {
        this.account = account;
        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        accessButton = new JButton("Access List");
        file = new JTextArea();
        accessButton.addActionListener(this);
        file.setBounds(150, 150, 200, 30);
        panel.add(file);
        file.setText("Enter the file name");
        backButton = new JButton("Back");
        accessButton.setBounds(150, 300, 200, 30);
        backButton.setBounds(150, 400, 200, 30);
        panel.add(backButton);
        panel.add(accessButton);
        backButton.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenu(account);
        }
        if (e.getSource() == accessButton) {
            boolean found = false;
            frame.dispose();
//            for (RFile f : Server.files) {
//                if (f.file.getName().equals(file.getText())&&f.getAccounts().contains(account)) {
//                    found = true;
//                }
//            }
            if (found) {
                new AccessFrame(account, file.getText());
            } else {
                new ViewAccessFrame(account);
            }
        }
    }
}
