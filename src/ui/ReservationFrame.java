package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.ReservationService;
import service.CancelService;
import service.TrainService;
import model.Train;

public class ReservationFrame extends JFrame implements ActionListener {

    JLabel passengerLabel, trainNoLabel, trainNameLabel;
    JLabel classLabel, dateLabel, sourceLabel, destinationLabel;

    JTextField passengerField, trainNoField, trainNameField;
    JTextField dateField, sourceField, destinationField;

    JComboBox<String> classBox;

    JButton reserveButton, cancelButton, fetchButton, viewButton ,searchButton ;

    public ReservationFrame() {

        setTitle("Online Reservation System");
        setSize(600,560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 242, 255));

        JLabel title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 140));
        title.setBounds(70, 10, 450, 35);
        add(title);

        passengerLabel = new JLabel("Passenger Name");
        passengerLabel.setBounds(50,70,120,25);
        add(passengerLabel);

        passengerField = new JTextField();
        passengerField.setBounds(200,70,200,25);
        add(passengerField);

        trainNoLabel = new JLabel("Train Number");
        trainNoLabel.setBounds(50,110,120,25);
        add(trainNoLabel);

        trainNoField = new JTextField();
        trainNoField.setBounds(200,110,200,25);
        add(trainNoField);


        fetchButton = new JButton("Fetch Train");
        fetchButton.setBounds(420,110,130,25);
        fetchButton.setBackground(new Color(0,120,215));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFocusPainted(false);
        fetchButton.addActionListener(this);
        add(fetchButton);

        trainNameLabel = new JLabel("Train Name");
        trainNameLabel.setBounds(50,150,120,25);
        add(trainNameLabel);

        trainNameField = new JTextField();
        trainNameField.setBounds(200,150,200,25);
        add(trainNameField);

        classLabel = new JLabel("Class Type");
        classLabel.setBounds(50,190,120,25);
        add(classLabel);

        classBox = new JComboBox<>();
        classBox.addItem("Sleeper");
        classBox.addItem("AC");
        classBox.addItem("First Class");
        classBox.setBounds(200,190,200,25);
        add(classBox);

        dateLabel = new JLabel("Journey Date");
        dateLabel.setBounds(50,230,120,25);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(200,230,200,25);
        add(dateField);

        sourceLabel = new JLabel("Source");
        sourceLabel.setBounds(50,270,120,25);
        add(sourceLabel);

        sourceField = new JTextField();
        sourceField.setBounds(200,270,200,25);
        add(sourceField);

        destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(50,310,120,25);
        add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(200,310,200,25);
        add(destinationField);

        reserveButton = new JButton("Reserve Ticket");
        reserveButton.setBounds(80,370,170,40);
        reserveButton.setBackground(new Color(0,120,125));
        reserveButton.setForeground(Color.WHITE);
        reserveButton.setFocusPainted(false);
        reserveButton.addActionListener(this);
        add(reserveButton);

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(320,370,170,40);
        cancelButton.setBackground(new Color(0,120,215));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(this);
        add(cancelButton);

        viewButton = new JButton("View Reservations");
        viewButton.setBounds(170,430,220,40);
        viewButton.setBackground(new Color(0,120,215));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFocusPainted(false);
        viewButton.addActionListener(this);
        add(viewButton);

        searchButton = new JButton("Search by PNR");
        searchButton.setBounds(170, 480, 220, 40);
        searchButton.setBackground(new Color(0,120,215));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(this);
        add(searchButton);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == reserveButton) {

            String passengerName = passengerField.getText().trim();

            if (passengerName.isEmpty() ||
                    trainNoField.getText().trim().isEmpty() ||
                    trainNameField.getText().trim().isEmpty() ||
                    dateField.getText().trim().isEmpty() ||
                    sourceField.getText().trim().isEmpty() ||
                    destinationField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill all the fields!",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int trainNo = Integer.parseInt(trainNoField.getText());
            String trainName = trainNameField.getText();
            String classType = (String) classBox.getSelectedItem();
            String journeyDate = dateField.getText();
            String source = sourceField.getText();
            String destination = destinationField.getText();

// Date Validation
            if (!journeyDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this,
                        "Enter date in YYYY-MM-DD format!",
                        "Invalid Date",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

// Source and Destination Validation
            if (source.equalsIgnoreCase(destination)) {
                JOptionPane.showMessageDialog(this,
                        "Source and Destination cannot be the same!",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            ReservationService service = new ReservationService();

            boolean success = service.reserveTicket(
                    passengerName,
                    trainNo,
                    trainName,
                    classType,
                    journeyDate,
                    source,
                    destination
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Reservation Successful!");

                passengerField.setText("");
                trainNoField.setText("");
                trainNameField.setText("");
                dateField.setText("");
                sourceField.setText("");
                destinationField.setText("");
                classBox.setSelectedIndex(0);

            } else {
                JOptionPane.showMessageDialog(this, "Reservation Failed!");
            }

        } else if (e.getSource() == fetchButton) {

        if (trainNoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Train Number!");
            return;
        }

        int trainNo = Integer.parseInt(trainNoField.getText().trim());

        TrainService trainService = new TrainService();



            Train train = trainService.getTrainDetails(trainNo);

            if (train != null) {

                trainNameField.setText(train.getTrainName());
                sourceField.setText(train.getSource());
                destinationField.setText(train.getDestination());

            } else {

                JOptionPane.showMessageDialog(this, "Train Not Found!");

            }

        } else if (e.getSource() == cancelButton) {

            String pnr = JOptionPane.showInputDialog(this, "Enter PNR:");

            CancelService cancelService = new CancelService();

            if (cancelService.cancelTicket(pnr)) {
                JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "PNR Not Found!");
            }
        } else if (e.getSource() == viewButton) {

            new ViewReservationFrame();
        }
        else if (e.getSource() == searchButton) {

            new SearchReservationFrame();

        }
    }

    }