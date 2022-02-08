package com.solution.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.solution.models.Event;
import com.solution.util.JDBCUtil;

public class HSQLDao {

	private static Logger LOGGER = Logger.getLogger(HSQLDao.class.getName()); 
	
	public static synchronized void saveEvent(Event tempEvent) {
		try (Connection connection = JDBCUtil.getConnection();) {

	            PreparedStatement preparedStatement = connection.prepareStatement(HSQLRepository.INSERT_USERS_SQL);
	            preparedStatement.setString(1, tempEvent.getId());
	            preparedStatement.setInt(2, (int)tempEvent.getDuration());
	            preparedStatement.setString(3, tempEvent.getType());
	            preparedStatement.setString(4, tempEvent.getHost());
	            preparedStatement.setBoolean(5, tempEvent.isAlert());
	            preparedStatement.executeUpdate();
	            LOGGER.log(Level.INFO, "Event Saved Successfully");
        } catch (SQLException e) {
        	LOGGER.log(Level.SEVERE, "Error Saving Event "+e.getMessage());
        }
	}
	
	public static void createTable() {
		try (Connection connection = JDBCUtil.getConnection();) {

			Statement statement = connection.createStatement();
			statement.execute(HSQLRepository.createTableSQL);
			LOGGER.log(Level.SEVERE, "Table created succesfully");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error Creating Table "+e.getMessage());
		}
		
	}

	public static void readTable() {
		try (Connection connection = JDBCUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(HSQLRepository.QUERY);) {

			ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                float duration = rs.getFloat("duration");
                String type = rs.getString("type");
                String host = rs.getString("host");
                boolean alert = rs.getBoolean("alert");
                System.out.println(id + "," + duration + "," + type + "," + host + "," + alert);
            }
        } catch (SQLException e) {
        	LOGGER.log(Level.SEVERE, "Error reading Event "+e.getMessage());
        }
		
	}
}
