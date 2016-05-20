package it.uniroma3.persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Prodotto;
import it.uniroma3.persistence.DataSource;
import it.uniroma3.persistence.ProdottoDAO;

public class ProdottoDAOpostgres implements ProdottoDAO {

	private DataSource ds;
	private PreparedStatement statement;
	private Connection connection;

	public ProdottoDAOpostgres() {
		super();
		this.ds = new DataSource();

	}

	@Override
	public boolean insert(String codiceProdotto, String nome,
			String descrizione, double prezzo) throws PersistenceException {

		Boolean b = false;
		this.connection = ds.getConnection();
		String insert = "INSERT INTO prodotto(idprodotto, codiceprodotto, nome, descrizione, prezzo) VALUES(?,?,?,?,?)";

		try {

			this.statement = this.connection.prepareStatement(insert);

			Long idProdotto = IdBroker.getId(connection, "idprodotto");

			this.statement.setLong(1, idProdotto);
			this.statement.setString(2, codiceProdotto);
			this.statement.setString(3, nome);
			this.statement.setString(4, descrizione);
			this.statement.setDouble(5, prezzo);

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

	@Override
	public List<Prodotto> findAll() throws PersistenceException {

		this.connection = ds.getConnection();
		Prodotto p;
		List<Prodotto> prodotti = null;

		String find = "SELECT * FROM prodotto";
		try {

			this.statement = this.connection.prepareStatement(find);
			ResultSet result = this.statement.executeQuery();

			prodotti = new ArrayList<Prodotto>();

			while (result.next()) {
				p = new Prodotto();
				p.setIdProdotto(result.getLong("idprodotto"));
				p.setCodiceProdotto(result.getString("codiceprodotto"));
				p.setNome(result.getString("nome"));
				p.setDescrizione(result.getString("descrizione"));
				p.setPrezzo(result.getDouble("prezzo"));

				prodotti.add(p);
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
		return prodotti; // null se problemi
	}

	@Override
	/* Ricerca prodotto in base a codiceprodotto (UNIQUE) */
	public Prodotto findByCodice(String codice) throws PersistenceException {

		this.connection = ds.getConnection();
		Prodotto p = null;

		String find = "SELECT * FROM prodotto WHERE codiceprodotto = ?";
		try {

			this.statement = this.connection.prepareStatement(find);
			this.statement.setString(1, codice);

			ResultSet result = this.statement.executeQuery();
			if (result.next()) {
				p = new Prodotto();
				p.setIdProdotto(result.getLong("idProdotto"));
				p.setCodiceProdotto(result.getString("codiceProdotto"));
				p.setNome(result.getString("nome"));
				p.setDescrizione(result.getString("descrizione"));
				p.setPrezzo(result.getDouble("prezzo"));
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
		return p;

	}

	@Override
	/* Ricerca di un prodotto in base idprodotto(CHIAVE) */
	public Prodotto findById(Long id) throws PersistenceException {

		this.connection = ds.getConnection();
		Prodotto p = null;
		String find = "SELECT * FROM prodotto WHERE idprodotto = ?";

		try {

			this.statement = this.connection.prepareStatement(find);
			this.statement.setLong(1, id);

			ResultSet result = this.statement.executeQuery();
			if (result.next()) {
				p = new Prodotto();
				p.setIdProdotto(result.getLong("idProdotto"));
				p.setCodiceProdotto(result.getString("codiceProdotto"));
				p.setNome(result.getString("nome"));
				p.setDescrizione(result.getString("descrizione"));
				p.setPrezzo(result.getDouble("prezzo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (this.statement != null)
					this.statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}

		}
		return p; // null se non esiste

	}
}
