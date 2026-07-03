package ui;

import service.ViewReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ViewReservationFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewReservationFrame() {

        setTitle("View Reservations");
        setSize(900,400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();

        model.addColumn("PNR");
        model.addColumn("Passenger");
        model.addColumn("Train No");
        model.addColumn("Train Name");
        model.addColumn("Class");
        model.addColumn("Journey Date");
        model.addColumn("Source");
        model.addColumn("Destination");

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();

        setVisible(true);
    }

    private void loadData() {

        try {

            ViewReservationService service = new ViewReservationService();

            ResultSet rs = service.getReservations();

            while(rs.next()) {

                model.addRow(new Object[]{

                        rs.getString("pnr"),
                        rs.getString("passenger_name"),
                        rs.getInt("train_no"),
                        rs.getString("train_name"),
                        rs.getString("class_type"),
                        rs.getString("journey_date"),
                        rs.getString("source"),
                        rs.getString("destination")

                });

            }

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}