package it.uniroma3.action;

import it.uniroma3.model.Ordine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionEvadiOrdine extends Action {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		String esito = "nonEvaso";
		HttpSession session = request.getSession();
		Ordine ordine = (Ordine) session.getAttribute("ordine");
		ordine.setStato("evaso");
		if (request.getParameter("evadi").equals("Evadi l'ordine")){
			if (facade.evadiOrdine(ordine)){
				esito = "evaso";
				session.setAttribute("ordine", ordine);
			}
		}
		else if (request.getParameter("evadi").equals("Torna indietro"))
				esito = "annullato";
		return esito;
	}

}
