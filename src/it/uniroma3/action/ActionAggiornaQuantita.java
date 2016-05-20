package it.uniroma3.action;

import it.uniroma3.model.Ordine;
import it.uniroma3.model.RigaOrdine;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionAggiornaQuantita extends Action {

	@Override
	public String esegui(HttpServletRequest request) {

		int nQuant = Integer.parseInt(request.getParameter("quantita"));
		String codProd = request.getParameter("codProd");
		HttpSession session = request.getSession();
		Ordine ordine = (Ordine) session.getAttribute("ordine");

		RigaOrdine r = (RigaOrdine) ordine.getRigheOrdine().get(codProd);

		if (nQuant == 0)
			/* Rimuovi la riga dalla mappa */
			ordine.getRigheOrdine().remove(codProd);
		else {
			/* Aggiorna la riga */
			r.setQuantita(nQuant);
			r.setPrezzo(nQuant * r.getProdotto().getPrezzo());
		}

		return ("OKAggiorna");
	}

}
