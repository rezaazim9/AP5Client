package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton uploadButton;
    JButton backButton;
    public UploadFrame() {
        frame = new JFrame();
        panel = new JPanel();
        uploadButton = new JButton("Upload");
        backButton = new JButton("Back");
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        uploadButton.setBounds(150, 100, 200, 30);
        panel.add(uploadButton);
        backButton.setBounds(150, 400, 200, 30);
        backButton.addActionListener(this);
        panel.add(backButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenu();
        }
    }
}
