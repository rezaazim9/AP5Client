package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloadFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton downloadButton;
    JButton backButton;

    public DownloadFrame() {
        frame = new JFrame();
        panel = new JPanel();
        downloadButton = new JButton("Download");
        backButton = new JButton("Back");
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        downloadButton.setBounds(150, 100, 200, 30);
        panel.add(downloadButton);
        backButton.setBounds(150, 400, 200, 30);
        panel.add(backButton);
        backButton.addActionListener(this);
        downloadButton.addActionListener(this);
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
