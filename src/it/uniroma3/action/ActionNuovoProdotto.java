package it.uniroma3.action;

import it.uniroma3.helper.HelperNuovoProdotto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionNuovoProdotto extends Action {

	public ActionNuovoProdotto() {

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();
		HelperNuovoProdotto helper = new HelperNuovoProdotto(request);

		String campiValidi = "campiValidiProd";
		String nome = request.getParameter("nomeP");
		String codice = request.getParameter("codice");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzoP");

		request.setAttribute("nomeP", nome);
		request.setAttribute("codice", codice);
		request.setAttribute("descrizione", descrizione);
		request.setAttribute("prezzoP", prezzo);

		if (helper.convalida()) {
			session.setAttribute("nomeP", nome);
			session.setAttribute("codice", codice);
			session.setAttribute("descrizione", descrizione);
			session.setAttribute("prezzoP", prezzo);
		} else {
			campiValidi = "campiNONValidiProd";
			request.setAttribute("errore", "valori errati");
		}

		return campiValidi;
	}
}
