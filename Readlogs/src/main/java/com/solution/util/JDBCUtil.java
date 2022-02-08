package com.solution.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCUtil {

	private static Logger LOGGER = Logger.getLogger(JDBCUtil.class.getName()); 
	private static Properties properties = new Properties();
	
	static {
		try(FileReader reader=new FileReader("src/main/resources/db.properties");){
			properties.load(reader);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error reading Database "+e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	public static Connection getConnection() {
        Connection connection = null;
        try { 
        	connection = DriverManager.getConnection(properties.getProperty("jdbcURL"), properties.getProperty("jdbcUsername"), properties.getProperty("jdbcPassword"));
        	LOGGER.log(Level.SEVERE, "Successfully Connected to Database ");
        }catch (SQLException e) {
        	if (connection!=null)
				try {
					connection.close();
				} catch (SQLException e1) {
					LOGGER.log(Level.SEVERE, "Error closing Connection "+e.getMessage());
				}
        	LOGGER.log(Level.SEVERE, "Error Connecting Database "+e.getMessage());
		} 
        return connection;
    }
}
