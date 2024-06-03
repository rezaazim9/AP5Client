package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton backButton;
    public ViewFrame() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(450, 150, 500, 500);
        frame.add(panel);
        panel.setLayout(null);
        backButton = new JButton("Back");
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
