package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CancelService {

    public boolean cancelTicket(String pnr) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM reservations WHERE pnr = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pnr);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}