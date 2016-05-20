package it.uniroma3.persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Ordine;
import it.uniroma3.model.RigaOrdine;
import it.uniroma3.persistence.DataSource;
import it.uniroma3.persistence.RigaOrdineDAO;

public class RigaOrdineDAOpostgres implements RigaOrdineDAO {

	private DataSource ds;
	private PreparedStatement statement;
	private Connection connection;

	public RigaOrdineDAOpostgres() {
		super();
		this.ds = new DataSource();

	}

	@Override
	public boolean insert(RigaOrdine riga, Ordine ordine)
			throws PersistenceException {

		Boolean b = false;
		this.connection = ds.getConnection();
		String insert = "INSERT INTO rigaordine(quantita, prezzo,codprodotto, idordine) VALUES(?,?,?,?)";

		try {

			this.statement = this.connection.prepareStatement(insert);
			this.statement.setInt(1, riga.getQuantita());
			this.statement.setDouble(2, riga.getPrezzo());
			this.statement.setString(3, riga.getProdotto().getCodiceProdotto());
			this.statement.setLong(4, ordine.getIdOrdine());

			b = (this.statement.executeUpdate() != 0);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (this.statement != null)
					this.statement.close();
				if (this.connection != null)
					this.connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return b;
	}
}
