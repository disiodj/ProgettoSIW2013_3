package it.uniroma3.action;

import it.uniroma3.model.Ordine;
import it.uniroma3.model.RigaOrdine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionConfAcquisto extends Action {

	public ActionConfAcquisto() {

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String esito;

		if (request.getParameter("si/no").equals("No"))
			esito = "acquistoNONconf";

		else {

			Ordine ordine = (Ordine) session.getAttribute("ordine");
			ordine.setStato("chiuso");

			boolean app = facade.chiudiOrdine(ordine);

			for (RigaOrdine r : ordine.getRigheOrdine().values())
				app &= facade.aggiungiRiga(r, ordine);

			if (app)
				esito = "acquistoRegistrato";
			else
				esito = "erroreAcquisto";

		}

		return esito;

	}
}
