package it.uniroma3.model;

public class Cliente extends Utente {
	private String indirizzo;
	private String email;
	private String role;

	// private List<Ordine> listaOrdini; Servirà conoscere gli ordini??

	public Cliente() {
		this.role = "user";

	}

	public Cliente(String indirizzo, String email) {
		this.role = "user";
		this.indirizzo = indirizzo;
		this.email = email;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
