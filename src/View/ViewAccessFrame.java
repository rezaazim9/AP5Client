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
    public ViewAccessFrame(Account account) {
        this.account=account;
         frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
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
            new MainMenu(account);
        }
    }
}
