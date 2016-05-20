package it.uniroma3.action;

import it.uniroma3.model.Ordine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionAnnullaAcquisto extends Action{

	public ActionAnnullaAcquisto() {
	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String esito;

		if (request.getParameter("si/no").equals("No"))
			esito = "annullaNONconf";

		else {

			Ordine ordine = (Ordine) session.getAttribute("ordine");

			if (facade.annullaOrdine(ordine))

				esito = "acquistoAnnullato";
			else
				esito = "erroreAnnulla";

		}

		return esito;

	}
}
