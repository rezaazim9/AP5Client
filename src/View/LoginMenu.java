package View;

import Controller.Server;
import Model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginMenu implements ActionListener {
    JFrame frame;
    JButton loginButton;
    JButton registerButton;
    JLabel mainLabel;
    JPanel panel;
    JLabel nameLabel;
    JLabel passwordLabel;
    JTextArea name;
    JPasswordField password;
    JButton exitButton;

    public LoginMenu() {
        frame = new JFrame();
        panel = new JPanel();
        name = new JTextArea();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        nameLabel = new JLabel("Name");
        mainLabel = new JLabel("Reza's File Sharing System");
        passwordLabel = new JLabel("Password");
        exitButton = new JButton("Exit");
        password = new JPasswordField();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.add(name);
        panel.setLayout(null);
        panel.add(password);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(exitButton);
        panel.add(mainLabel);
        panel.add(passwordLabel);
        panel.add(nameLabel);
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBounds(150, 400, 200, 30);
        name.setBounds(150, 150, 200, 30);
        loginButton.setBounds(150, 300, 200, 30);
        registerButton.setBounds(150, 350, 200, 30);
        password.setBounds(150, 250, 200, 30);
        password.setFont(new Font("Arial", Font.PLAIN, 25));
        mainLabel.setBounds(100, 50, 300, 50);
        mainLabel.setForeground(Color.BLUE);
        mainLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        name.setFont(new Font("Arial", Font.PLAIN, 25));
        passwordLabel.setBounds(150, 200, 200, 50);
        nameLabel.setBounds(150, 100, 200, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (Account.accounts.stream().anyMatch(account -> account.name.equals(name.getText()) && account.password.equals(Server.hashPassword(password.getText())))) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                frame.dispose();
                new MainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Login Failed");
            }
        }
        if (e.getSource() == registerButton) {
            if (Account.accounts.stream().anyMatch(account -> account.name.equals(name.getText()))) {
                JOptionPane.showMessageDialog(frame, "Account already exists");
            } else {

                Account.accounts.add(new Account(name.getText(), Server.hashPassword(password.getText()), new ArrayList<>()));
                JOptionPane.showMessageDialog(frame, "Account created");
                frame.dispose();
                new MainMenu();
            }
        }
    }
}
