package it.uniroma3.persistence.postgres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Cliente;
import it.uniroma3.model.Ordine;

import it.uniroma3.persistence.DataSource;
import it.uniroma3.persistence.OrdineDAO;

public class OrdineDAOpostgres implements OrdineDAO {

	private DataSource ds;
	private PreparedStatement statement;
	private Connection connection;

	public OrdineDAOpostgres() {
		super();
		this.ds = new DataSource();

	}

	@Override
	public Long generaID() throws PersistenceException {

		Long idOrdine;
		this.connection = ds.getConnection();
		idOrdine = IdBroker.getId(connection, "sequenza_id_ordine");

		return idOrdine;
	}

	@Override
	public boolean insert(Ordine ordine) throws PersistenceException {

		this.connection = ds.getConnection();

		boolean inserito = false;
		String insert = "INSERT INTO ordine(idordine, idcliente, stato, data) VALUES(?,?,?,?)";

		try {
			this.statement = this.connection.prepareStatement(insert);
			this.statement.setLong(1, ordine.getIdOrdine());
			this.statement.setLong(2, ordine.getCliente().getIdUtente());
			this.statement.setString(3, ordine.getStato());
			Date dataSQL = new Date(ordine.getData().getTime());
			this.statement.setDate(4, dataSQL);

			inserito = (this.statement.executeUpdate() != 0);

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
		return inserito;

	}

	@Override
	public boolean update(Ordine ordine) throws PersistenceException {

		this.connection = ds.getConnection();
		boolean up = false;
		String update = "update ordine set  stato=? where idordine=?";

		try {
			this.statement = this.connection.prepareStatement(update);
			this.statement.setString(1, ordine.getStato());
			this.statement.setLong(2, ordine.getIdOrdine());
			up = (this.statement.executeUpdate() != 0);

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

		return up;
	}

	@Override
	public boolean delete(Ordine ordine) throws PersistenceException {

		this.connection = ds.getConnection();
		boolean del = false;
		String delete = "delete from ordine where idordine=?";

		try {
			this.statement = this.connection.prepareStatement(delete);
			this.statement.setLong(1, ordine.getIdOrdine());
			del = (this.statement.executeUpdate() != 0);

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

		return del;
	}

	@Override
	public Ordine findById(Long idOrdine) throws PersistenceException {

		this.connection = ds.getConnection();
		Ordine ordine = null;

		String find = "select * from ordine where idordine = ?";
		try {
			this.statement = this.connection.prepareStatement(find);
			this.statement.setLong(1, idOrdine);
			ResultSet result = this.statement.executeQuery();

			if (result.next()) {
				ordine = new OrdineProxy();
				/* Lazy-load? */
				ordine.setIdOrdine(result.getLong("idordine"));
				ordine.setData(result.getDate("data"));
				ordine.setStato(result.getString("stato"));
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
		return ordine;
	}

	@Override
	public List<Ordine> findByIdCliente(Cliente cliente)
			throws PersistenceException {

		this.connection = ds.getConnection();
		Ordine o;
		List<Ordine> ordini = new ArrayList<Ordine>();

		String find = "select * from ordine where idcliente = ?";

		try {
			this.statement = this.connection.prepareStatement(find);
			this.statement.setLong(1, cliente.getIdUtente());
			ResultSet result = this.statement.executeQuery();

			while (result.next()) {
				o = new Ordine();
				o.setIdOrdine(result.getLong("idordine"));
				o.setData(result.getDate("data"));
				o.setStato(result.getString("stato"));
				o.setCliente(cliente);

				ordini.add(o);
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
		return ordini;
	}

	@Override
	public List<Ordine> findAllClose() throws PersistenceException {

		this.connection = ds.getConnection();
		List<Ordine> ordini = null;

		String find = "select * from ordine where stato = ?";

		try {
			this.statement = this.connection.prepareStatement(find);
			this.statement.setString(1, "chiuso");
			ResultSet result = statement.executeQuery();
			ordini = new ArrayList<Ordine>();

			while (result.next()) {
				Ordine ordine = new OrdineProxy();
				/* Lazy-load?? */

				ordine.setData(result.getDate("data"));
				ordine.setIdOrdine(result.getLong("idordine"));
				ordine.setStato(result.getString("stato"));
				ordini.add(ordine);

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
		return ordini;
	}

}
