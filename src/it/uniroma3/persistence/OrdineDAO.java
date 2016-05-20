package it.uniroma3.persistence;

import java.util.List;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Cliente;
import it.uniroma3.model.Ordine;

/*ORDINE È UN OGGETTO AGGREGATO*/

public interface OrdineDAO {

	/* Genera un ID per il prossimo prodotto da inserire */
	public Long generaID() throws PersistenceException;

	/* Aggiunge un nuovo aggregato denl DB */
	public boolean insert(Ordine ordine) throws PersistenceException;

	/* Aggiorna un ordine nel DB */
	public boolean update(Ordine ordine) throws PersistenceException;

	/* Cancella un ordine dal db */
	boolean delete(Ordine ordine) throws PersistenceException;

	/* Prende l'aggregato per chiave primaria */
	public Ordine findById(Long idOrdine) throws PersistenceException;

	/* Restituisce la lista degli ordini di un cliente */
	public List<Ordine> findByIdCliente(Cliente cliente)
			throws PersistenceException;

	/* Restituisce tutti gli ordini chiusi */
	public List<Ordine> findAllClose() throws PersistenceException;

}
