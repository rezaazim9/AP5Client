package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
        return new Account(name.getText(), hashPassword(password.getText()), new ArrayList<>(), new JWT(hashPassword(name.getText() + password.getText()), name.getText()), new ArrayList<>());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                Socket socket = new Socket("localhost", 1111);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream.writeObject(new Packet(createAccount(), "login"));
                if ((boolean) inputStream.readObject()) {
                    account = createAccount();
                    JOptionPane.showMessageDialog(frame, "Login successful");
                    frame.dispose();
                    new MainMenu(account);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password");
                }
                socket.close();
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == registerButton) {
            try {
                Socket socket = new Socket("localhost", 1111);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream.writeObject(new Packet(createAccount(), "register"));
                if ((boolean) inputStream.readObject()) {
                    account = createAccount();
                    JOptionPane.showMessageDialog(frame, "Register successful");
                    frame.dispose();
                    new MainMenu(account);
                } else {
                    JOptionPane.showMessageDialog(frame, "Username already exists");
                }
                socket.close();
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
