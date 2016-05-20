package it.uniroma3.model;

public class Amministratore extends Utente {
	private String role;

	public Amministratore() {
		this.role = "admin";

	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
