package it.uniroma3.persistence.postgres;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Amministratore;
import it.uniroma3.model.Cliente;
import it.uniroma3.model.Ordine;
import it.uniroma3.model.Utente;
import it.uniroma3.persistence.DataSource;
import it.uniroma3.persistence.UtenteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAOpostgres implements UtenteDAO {

	private DataSource ds = new DataSource();
	private Connection connection;
	private PreparedStatement statement;

	public UtenteDAOpostgres() {
		super();
		this.ds = new DataSource();

	}

	@Override
	public boolean insert(String nome, String cognome, String indirizzo,
			String email, String username, String password)
			throws PersistenceException {

		Boolean b = false;
		this.connection = this.ds.getConnection();
		String insert = "INSERT INTO utente(idutente, nome, cognome, indirizzo, email, username, password, role) VALUES(?,?,?,?,?,?,?,?)";

		try {

			this.statement = this.connection.prepareStatement(insert);
			Long idUtente = IdBroker.getId(connection, "sequenza_id_utente");
			this.statement.setLong(1, idUtente);

			this.statement.setString(2, nome);
			this.statement.setString(3, cognome);
			this.statement.setString(4, indirizzo);
			this.statement.setString(5, email);
			this.statement.setString(6, username);
			this.statement.setString(7, password);
			this.statement.setString(8, "user");

			b = (this.statement.executeUpdate() != 0);
			// per aggiornamenti, ritorna numero righe aggiornate(o 0)

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
		return b; // se false, non puo aggiugere-->problema di chiavi
	}

	@Override
	public Utente findByUsername(String username) throws PersistenceException {

		this.connection = this.ds.getConnection();
		String query = "SELECT * FROM utente WHERE username=?";
		Utente utente = null;

		try {

			this.statement = this.connection.prepareStatement(query);
			this.statement.setString(1, username);
			ResultSet result = this.statement.executeQuery();

			if (result.next()) {
				if (result.getString("role").equals("user")) {
					utente = new Cliente(result.getString("indirizzo"),
							result.getString("email")); // this.role="user"

				} else
					utente = new Amministratore(); // this.role ="admin"

				utente.setIdUtente(result.getLong("idutente"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setUsername(result.getString("username"));
				utente.setPassword(result.getString("password"));
			}

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

		return utente;
	}

	@Override
	public Cliente findByOrdine(Ordine ordine) throws PersistenceException {

		this.connection = this.ds.getConnection();
		String query = "SELECT * FROM utente,ordine WHERE ordine.idcliente = utente.idutente and ordine.idordine=?";
		Cliente cliente = null;

		try {

			this.statement = this.connection.prepareStatement(query);
			this.statement.setLong(1, ordine.getIdOrdine());
			ResultSet result = this.statement.executeQuery();

			if (result.next()) {
				cliente = new Cliente();
				cliente.setIdUtente(result.getLong("idutente"));
				cliente.setNome(result.getString("nome"));
				cliente.setCognome(result.getString("cognome"));
				cliente.setUsername(result.getString("username"));
				cliente.setEmail(result.getString("email"));
				cliente.setIndirizzo(result.getString("indirizzo"));

			}

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

		return cliente;// null se non esiste
	}

}
