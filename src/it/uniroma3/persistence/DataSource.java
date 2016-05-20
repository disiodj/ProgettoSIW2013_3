package it.uniroma3.persistence;

import it.uniroma3.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataSource {
	private String dbURI = "jdbc:postgresql://localhost/azienda";
	private String userName = "postgres";
	private String password = "postgres";

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
		    Class.forName("org.postgresql.Driver");
		    connection = DriverManager.getConnection(dbURI,userName, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}