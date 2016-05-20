package it.uniroma3.action;

import it.uniroma3.model.Ordine;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionConsTuttiOrdini extends Action {

	public ActionConsTuttiOrdini() {

	}

	@Override
	public String esegui(HttpServletRequest request) {

		String esito = "erroreOrdini";
		HttpSession session = request.getSession();
		
		List<Ordine> ordini = facade.getOrdiniChiusi();

		if (ordini != null) {
			esito = "OKOrdini";
			session.setAttribute("ordini", ordini);
		}
		return esito;
	}
}
