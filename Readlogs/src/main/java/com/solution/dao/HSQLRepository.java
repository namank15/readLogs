package com.solution.dao;

public class HSQLRepository {
	
	public static final String createTableSQL = "create table Events (\r\n" + "  id  varchar(20) primary key,\r\n" +
	        "  duration int not null,\r\n" + "  type varchar(20),\r\n" + "  host varchar(20),\r\n" +
	        "  alert bit(1)\r\n" + "  );";

	public static final String INSERT_USERS_SQL = "INSERT INTO Events" +
	        "  (id, duration, type, host, alert) VALUES " +
	        " (?, ?, ?, ?, ?);";
	public static final String QUERY = "select * from Events";

}
