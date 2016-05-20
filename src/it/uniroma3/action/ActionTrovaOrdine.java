package it.uniroma3.action;

import it.uniroma3.model.Ordine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionTrovaOrdine extends Action {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession session = request.getSession();
		String esito = "KOidOrd";
		try {
			Long idOrdine = Long.parseLong(request.getParameter("idOrdine"));
			Ordine ordine = facade.getOrdine(idOrdine);
			if (ordine != null) {
				esito = "trovato";
				session.setAttribute("ordine", ordine);
				if (!ordine.getStato().equals("chiuso"))
					esito = "ordineNonChiuso";
			}else{
				request.setAttribute("erroreId", "L'ID inserito non esiste");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("erroreId", "Devi inserire un ID numerico");
		}
		return esito;

	}
}
