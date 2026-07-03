package service;

import database.DBConnection;
import model.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainService {

    public Train getTrainDetails(int trainNo) {

        Train train = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM trains WHERE train_no = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, trainNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                train = new Train();

                train.setTrainNo(rs.getInt("train_no"));
                train.setTrainName(rs.getString("train_name"));
                train.setSource(rs.getString("source"));
                train.setDestination(rs.getString("destination"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return train;
    }
}