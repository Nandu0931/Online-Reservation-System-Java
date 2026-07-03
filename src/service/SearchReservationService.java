package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchReservationService {

    public ResultSet searchReservation(String pnr) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM reservations WHERE pnr = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pnr);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}