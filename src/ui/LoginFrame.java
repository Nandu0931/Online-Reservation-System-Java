package ui;

import service.LoginService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel title, userLabel, passLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginFrame() {

        setTitle("Online Reservation System");
        setSize(450,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(40,20,350,30);
        add(title);

        userLabel = new JLabel("Username");
        userLabel.setBounds(50,80,100,25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160,80,180,25);
        add(usernameField);

        passLabel = new JLabel("Password");
        passLabel.setBounds(50,130,100,25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160,130,180,25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(160,190,100,35);
        loginButton.addActionListener(this);
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        LoginService service = new LoginService();

        if (service.login(username, password)) {

            JOptionPane.showMessageDialog(this, "Login Successful!");

            dispose();

            new ReservationFrame();

        } else {

            JOptionPane.showMessageDialog(this, "Invalid Username or Password!");

        }
    }
}