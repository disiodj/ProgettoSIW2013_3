package it.uniroma3.model;

public class RigaOrdine {

	private int quantita;
	private double prezzo;
	private Prodotto prodotto;


	public RigaOrdine() {

	}

	public Prodotto getProdotto() {
		return this.prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	



}
