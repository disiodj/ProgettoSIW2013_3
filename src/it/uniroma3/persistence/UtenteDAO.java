package it.uniroma3.persistence;

import it.uniroma3.exceptions.PersistenceException;

import it.uniroma3.model.Cliente;
import it.uniroma3.model.Ordine;
import it.uniroma3.model.Utente;

public interface UtenteDAO {

	boolean insert(String nome, String cognome, String indirizzo, String email,
			String username, String password) throws PersistenceException;

	public Utente findByUsername(String username) throws PersistenceException;

	public Cliente findByOrdine(Ordine ordine) throws PersistenceException;

}
