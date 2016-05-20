package it.uniroma3.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.model.Cliente;

import it.uniroma3.model.Ordine;
import it.uniroma3.model.Prodotto;
import it.uniroma3.model.RigaOrdine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionNuovoOrdine extends Action {

	public ActionNuovoOrdine() {

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String esito = "erroreOrdine";
		
		Ordine ordine = new Ordine();
		
		Cliente clienteCorrente = (Cliente) session.getAttribute("utente");
		ordine.setCliente(clienteCorrente);
		Date data = new Date();
		ordine.setData(data);
		Long idO = facade.getNewIdOrdine();
		ordine.setIdOrdine(idO);
		Map<String, RigaOrdine> righe = new HashMap<String, RigaOrdine>();
		ordine.setRigheOrdine(righe);
		ordine.setStato("aperto");

		boolean inserito = facade.creaOrdine(ordine);
		List<Prodotto> prodotti = facade.getProdotti();

		if (inserito && (prodotti != null)) {
			session.setAttribute("ordine", ordine);
			session.setAttribute("prodotti", prodotti);
			esito = "ordineOK";
		}
		return esito;

	}

}
