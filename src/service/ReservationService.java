package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class ReservationService {

    public boolean reserveTicket(String passengerName,
                                 int trainNo,
                                 String trainName,
                                 String classType,
                                 String journeyDate,
                                 String source,
                                 String destination) {

        try {

            Connection con = DBConnection.getConnection();

            String pnr = UUID.randomUUID().toString().substring(0,8).toUpperCase();

            String sql = "INSERT INTO reservations(pnr, passenger_name, train_no, train_name, class_type, journey_date, source, destination) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pnr);
            ps.setString(2, passengerName);
            ps.setInt(3, trainNo);
            ps.setString(4, trainName);
            ps.setString(5, classType);
            ps.setString(6, journeyDate);
            ps.setString(7, source);
            ps.setString(8, destination);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}