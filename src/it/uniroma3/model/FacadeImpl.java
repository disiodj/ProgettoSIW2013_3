package it.uniroma3.model;

import java.util.List;

import it.uniroma3.exceptions.PersistenceException;
import it.uniroma3.persistence.postgres.OrdineDAOpostgres;
import it.uniroma3.persistence.postgres.ProdottoDAOpostgres;
import it.uniroma3.persistence.postgres.RigaOrdineDAOpostgres;
import it.uniroma3.persistence.postgres.UtenteDAOpostgres;

public class FacadeImpl implements Facade {

	private static FacadeImpl instance;
	private UtenteDAOpostgres utenteDAOpostgres = new UtenteDAOpostgres();
	private OrdineDAOpostgres ordineDAOpostgres = new OrdineDAOpostgres();
	private ProdottoDAOpostgres prodottoDAOpostgres = new ProdottoDAOpostgres();
	private RigaOrdineDAOpostgres rigaordineDAOpostgres = new RigaOrdineDAOpostgres();

	public static synchronized FacadeImpl getInstance() {
		if (instance == null)
			instance = new FacadeImpl();
		return instance;
	}

	/* Aggiunge un nuovo utente nel db */
	@Override
	public boolean registraCliente(String nome, String cognome,
			String indirizzo, String email, String username, String password) {

		boolean inserimentoRiuscito = false;

		try {
			inserimentoRiuscito = utenteDAOpostgres.insert(nome, cognome,
					indirizzo, email, username, password);

		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return inserimentoRiuscito;// false se problemi

	}

	/* Aggiunge un nuovo prodotto nel DB */
	@Override
	public boolean aggiungiProdotto(String nome, String descrizione,
			double prezzo, String codiceProdotto) {
		boolean inserito = false;
		try {
			inserito = (prodottoDAOpostgres.insert(codiceProdotto, nome,
					descrizione, prezzo));
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return inserito;

	}

	/* Ritorna una lista dei prodotti presenti nel DB (CATALOGO) */
	@Override
	public List<Prodotto> getProdotti() {

		List<Prodotto> prodotti = null;
		try {
			prodotti = prodottoDAOpostgres.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return prodotti;// null se nn esiste
	}

	/* Ritorna un prodotto in base a codiceprodotto(UNIQUE) */
	@Override
	public Prodotto getProdotto(String codice) {
		ProdottoDAOpostgres db = new ProdottoDAOpostgres();
		Prodotto prodotto = null;
		try {
			prodotto = db.findByCodice(codice);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return prodotto;// null se non esiste

	}

	/* Ritorna l'utente autenticato se esiste, null altrimenti */
	@Override
	public Utente autentica(String username, String password) {

		Utente utente = null;

		try {
			Utente temp;
			temp = utenteDAOpostgres.findByUsername(username);
			if (temp != null && temp.getPassword() != null
					&& temp.getPassword().equals(password))
				utente = temp;
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return utente;// null se non esiste
	}

	/* Aggiunge un nuovo ordine (vuoto e con stato "aperto") nel db */
	@Override
	public boolean creaOrdine(Ordine ordine) {

		boolean inserito = false;

		try {
			inserito = ordineDAOpostgres.insert(ordine);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return inserito; // null se problemi
	}

	/* Aggiunge una nuova riga ordine nel db */
	@Override
	public boolean aggiungiRiga(RigaOrdine riga, Ordine ordine) {
		boolean creata = false;
		try {
			creata = (rigaordineDAOpostgres.insert(riga, ordine));
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return creata; // false se problemi

	}

	/* Chiude un ordine, settando a "chiuso" lo stato */
	@Override
	public boolean chiudiOrdine(Ordine ordine) {
		boolean chiuso = false;

		try {

			chiuso = (ordineDAOpostgres.update(ordine));
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return chiuso;

	}

	/* Annulla un ordine (aperto) */
	@Override
	public boolean annullaOrdine(Ordine ordine) {
		boolean annullato = false;

		try {
			annullato = ordineDAOpostgres.delete(ordine);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		return annullato;
	}

	/* Ritorna una lista degli ordini effettuati da un cliente */
	@Override
	public List<Ordine> getOrdiniByCliente(Cliente cliente) {

		OrdineDAOpostgres db = new OrdineDAOpostgres();
		List<Ordine> ordini = null;
		try {
			ordini = db.findByIdCliente(cliente);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return ordini;// null se problemi

	}

	/* Resitutuisce un ordine in base a idordine(CHIAVE) */
	@Override
	public Ordine getOrdine(Long idOrdine) {
		Ordine ordine = null;
		try {
			ordine = ordineDAOpostgres.findById(idOrdine);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return ordine;// null
	}

	/* Evade un ordine */
	@Override
	public boolean evadiOrdine(Ordine ordine) {
		boolean evadi = false;
		try {
			evadi = (ordineDAOpostgres.update(ordine));

		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return evadi;

	}

	/* Ritorna una lista degli Ordini presenti nel DB */
	@Override
	public List<Ordine> getOrdiniChiusi() {

		List<Ordine> ordini = null;

		try {
			ordini = ordineDAOpostgres.findAllClose();

		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return ordini;// null se nn esiste
	}

	@Override
	public Long getNewIdOrdine() {

		Long newId = null;
		try {
			newId = ordineDAOpostgres.generaID();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return newId;
	}
}
