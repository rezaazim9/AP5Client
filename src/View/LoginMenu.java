package View;

import Model.Account;
import Model.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    Account account;

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
        exitButton.addActionListener(e -> frame.dispose());
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
        frame.setVisible(true);
    }

    public static String hashPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public Account createAccount() {
        return new Account(name.getText(), hashPassword(password.getText()), new ArrayList<>(), new JWT(hashPassword(name.getText() + password.getText()), name.getText()));
    }

    public void writer(Account account) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("C:\\Users\\ostad\\IdeaProjects\\Request.json"), account);
    }

    public boolean reader() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        boolean response = objectMapper.readValue(new File("C:\\Users\\ostad\\IdeaProjects\\Response.json"), boolean.class);
        objectMapper.writeValue(new File("C:\\Users\\ostad\\IdeaProjects\\Response.json"), null);
        return response;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            writer(createAccount());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        boolean response;
//        try {
//            response = reader();
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
        if (e.getSource() == loginButton) {
            if (true) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                frame.dispose();
                new MainMenu(account);
            } else {
                JOptionPane.showMessageDialog(frame, "Login Failed");
            }
        }
        if (e.getSource() == registerButton) {
            if (false) {
                JOptionPane.showMessageDialog(frame, "Account already exists");
            } else {
//                String token = Server.hashPassword(name.getText() + password.getText());
//                JWT jwt = new JWT(token, name.getText());
//                JWT.jwtList.add(jwt);
//                account.setJwt(jwt);
//                accounts.add(account);
                JOptionPane.showMessageDialog(frame, "Account created");
                frame.dispose();
                new MainMenu(account);
            }
        }
    }
}
