package it.uniroma3.action;

import it.uniroma3.model.Prodotto;

import javax.servlet.http.HttpServletRequest;

public class ActionDettaglioProdotto extends Action{

	public ActionDettaglioProdotto() {

	}

	public String esegui(HttpServletRequest request) {


		// session = request.getSession();

		String esito = "erroreDettaglio";

		Prodotto prodotto = facade.getProdotto(request.getParameter("codProd"));

		if (prodotto != null) {
			request.setAttribute("prodotto", prodotto);
			esito = "dettaglioOK";
		}
		return esito;

	}

}
