package it.uniroma3.model;

import java.util.Date;

import java.util.Map;

public class Ordine {
	private Long idOrdine;
	private Date data;
	private Cliente cliente;
	private String stato;
	private Map<String, RigaOrdine> righeOrdine;

	public Ordine() {
	}


	public Long getIdOrdine() {
		return this.idOrdine;
	}

	public void setIdOrdine(Long id) {
		this.idOrdine = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Map<String, RigaOrdine> getRigheOrdine() {
		return this.righeOrdine;
	}

	public void setRigheOrdine(Map<String, RigaOrdine> righeOrdine) {
		this.righeOrdine = righeOrdine;
	}
	
	
}
