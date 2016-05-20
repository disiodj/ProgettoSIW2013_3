package it.uniroma3.persistence.postgres;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.model.Cliente;
import it.uniroma3.model.Ordine;

public class OrdineProxy extends Ordine {

	private boolean caricato = false;

	public Cliente getCliente() {

		if (!this.caricato) {
			Cliente cliente = null;
			UtenteDAOpostgres dao = new UtenteDAOpostgres();
			try {
				cliente = new Cliente();
				cliente = dao.findByOrdine(this);
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
			this.caricato = true;
			this.setCliente(cliente);
		}
		return super.getCliente();
	}

}
