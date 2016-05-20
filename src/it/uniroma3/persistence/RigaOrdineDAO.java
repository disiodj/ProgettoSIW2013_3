package it.uniroma3.persistence;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Ordine;
import it.uniroma3.model.RigaOrdine;

public interface RigaOrdineDAO {

	public boolean insert(RigaOrdine riga, Ordine ordine)
			throws PersistenceException;
}
