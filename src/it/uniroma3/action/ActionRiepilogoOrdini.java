package it.uniroma3.action;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.model.Cliente;
import it.uniroma3.model.Ordine;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionRiepilogoOrdini extends Action {

	public ActionRiepilogoOrdini() {

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();

		Cliente cliente = (Cliente) session.getAttribute("utente");
		String esito = "ErroreVisualOrdini";
		List<Ordine> ordini = new ArrayList<Ordine>();
		ordini = facade.getOrdiniByCliente(cliente);
		if (ordini != null) {
			session.setAttribute("ordini", ordini);
			esito = "visualOrdiniOK";
		}

		return esito;

	}
}
