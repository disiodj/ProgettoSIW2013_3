package it.uniroma3.persistence.postgres;

import it.uniroma3.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/*NE VANNO FATTI DUE PER DUE NUMERAZIONI DIVERSE*/

public class IdBroker {
	private static Logger logger = Logger
			.getLogger("it.uniroma3.persistence.jdbc.IdBroker");

	/* O seqeunza_id_utente o sequenza_id_prodotto */
	public static Long getId(Connection connection, String sequenza)
			throws PersistenceException {
		PreparedStatement statement = null;
		Long id = (long) -1;
		try {
			String query = "SELECT nextval ('"+sequenza+"') AS id";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return new Long(id);
	}
}
