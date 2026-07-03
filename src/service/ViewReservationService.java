package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewReservationService {

    public ResultSet getReservations() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            return st.executeQuery("SELECT * FROM reservations");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}