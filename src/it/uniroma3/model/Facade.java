package it.uniroma3.model;

import java.util.List;

public interface Facade {

	/* Aggiunge un nuovo utente nel db */
	public boolean registraCliente(String nome, String cognnome,
			String indirizzo, String email, String username, String password);

	/* Aggiunge un nuovo prodotto nel DB */
	public boolean aggiungiProdotto(String nome, String descrizione,
			double prezzo, String codiceProdotto);

	/* Ritorna una lista dei prodotti presenti nel DB */
	public List<Prodotto> getProdotti();

	/* Ritorna un prodotto in base a codiceprodotto(UNIQUE) */
	public Prodotto getProdotto(String codice);

	/* Ritorna l'utente autenticato se esiste, null altrimenti */
	public Utente autentica(String username, String password);

	/* Aggiunge un nuovo ordine (vuoto e con stato "aperto") nel db */
	public boolean creaOrdine(Ordine ordine);

	/* Aggiunge una nuova riga ordine nel db */
	boolean aggiungiRiga(RigaOrdine riga, Ordine ordine);

	/* Chiude un ordine, settando a "chiuso" lo stato */
	public boolean chiudiOrdine(Ordine ordine);

	/* Annulla un ordine (aperto) */
	public boolean annullaOrdine(Ordine ordine);

	/* Ritorna una lista degli ordini effettuati da un cliente */
	public List<Ordine> getOrdiniByCliente(Cliente cliente);

	/* Resitutuisce un ordine in base a idordine(CHIAVE) */
	public Ordine getOrdine(Long idOrdine);

	/* Evade un ordine */
	public boolean evadiOrdine(Ordine ordine);

	/* Ritorna una lista degli Ordini presenti nel DB */
	public List<Ordine> getOrdiniChiusi();

	/*Ritorna un nuovo IdOrdine*/
	public Long getNewIdOrdine();

}
