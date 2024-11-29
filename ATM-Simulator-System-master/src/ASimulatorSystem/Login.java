package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    Login() {
        setTitle("OUR BANK");

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/background.jpg"));
        Image img = backgroundImage.getImage().getScaledInstance(800, 480, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(img);
        JLabel backgroundLabel = new JLabel(scaledImage);
        backgroundLabel.setBounds(0, 0, 800, 480);

        // Set the layout to null
        setLayout(null);
        setContentPane(backgroundLabel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 10, 100, 100);
        backgroundLabel.add(l11);

        l1 = new JLabel("WELCOME TO BANK");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200, 40, 450, 50);
        backgroundLabel.add(l1);

        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125, 150, 375, 30);
        backgroundLabel.add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(300, 150, 230, 30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(tf1);

        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125, 220, 375, 30);
        backgroundLabel.add(l3);

        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300, 220, 230, 30);
        backgroundLabel.add(pf2);

        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);

        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300, 300, 100, 30);
        backgroundLabel.add(b1);

        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430, 300, 100, 30);
        backgroundLabel.add(b2);

        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300, 350, 230, 30);
        backgroundLabel.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setSize(800, 480);
        setLocation(550, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                Conn c1 = new Conn();
                String cardno = tf1.getText();
                String pin = pf2.getText();

                // Use a prepared statement to prevent SQL injection
                String q = "SELECT * FROM login WHERE card_number = ? AND pin = ?";
                PreparedStatement pst = c1.c.prepareStatement(q);
                pst.setString(1, cardno);
                pst.setString(2, pin);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
                pst.close(); // Close the prepared statement
            } else if (ae.getSource() == b2) {
                tf1.setText("");
                pf2.setText("");
            } else if (ae.getSource() == b3) {
                setVisible(false);
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
