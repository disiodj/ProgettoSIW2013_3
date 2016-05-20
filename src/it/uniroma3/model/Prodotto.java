package it.uniroma3.model;

public class Prodotto {
	private Long idProdotto;
	private String codiceProdotto;
	private String nome;
	private String descrizione;
	private Double prezzo;

	public Prodotto() {
	}

	public Long getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(Long idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getCodiceProdotto() {
		return this.codiceProdotto;
	}

	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}
