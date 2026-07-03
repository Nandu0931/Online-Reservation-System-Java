package ui;

import service.SearchReservationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchReservationFrame extends JFrame implements ActionListener {

    JLabel pnrLabel;
    JTextField pnrField;
    JButton searchButton;
    JTextArea resultArea;

    public SearchReservationFrame() {

        setTitle("Search Reservation");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pnrLabel = new JLabel("Enter PNR:");
        pnrLabel.setBounds(30, 30, 100, 25);
        add(pnrLabel);

        pnrField = new JTextField();
        pnrField.setBounds(140, 30, 200, 25);
        add(pnrField);

        searchButton = new JButton("Search");
        searchButton.setBounds(170, 70, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(30, 120, 420, 200);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SearchReservationService service = new SearchReservationService();

        try {
            System.out.println("Searching PNR: " + pnrField.getText().trim().toUpperCase());

            ResultSet rs = service.searchReservation(pnrField.getText().trim().toUpperCase());

            if (rs.next()) {

                resultArea.setText(
                        "PNR : " + rs.getString("pnr") +
                                "\nPassenger : " + rs.getString("passenger_name") +
                                "\nTrain No : " + rs.getInt("train_no") +
                                "\nTrain Name : " + rs.getString("train_name") +
                                "\nClass : " + rs.getString("class_type") +
                                "\nJourney Date : " + rs.getString("journey_date") +
                                "\nSource : " + rs.getString("source") +
                                "\nDestination : " + rs.getString("destination")
                );

            } else {

                JOptionPane.showMessageDialog(this, "PNR Not Found!");

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }
}