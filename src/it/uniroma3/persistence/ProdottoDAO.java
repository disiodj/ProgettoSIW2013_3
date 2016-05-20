package it.uniroma3.persistence;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Prodotto;

import java.util.List;

public interface ProdottoDAO {

	public boolean insert(String codiceProdotto, String nome,
			String descrizione, double prezzo) throws PersistenceException;

	public List<Prodotto> findAll() throws PersistenceException;

	public Prodotto findByCodice(String codice) throws PersistenceException;

	public Prodotto findById(Long id) throws PersistenceException;

}