package it.uniroma3.action;

import java.util.List;

import it.uniroma3.model.Prodotto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionVisualizzaCatalogo extends Action {

	public ActionVisualizzaCatalogo() {

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();

		String esito = "ErroreCatalogo";
		List<Prodotto> prodotti;
		prodotti = facade.getProdotti();
		if (prodotti != null) {
			session.setAttribute("prodotti", prodotti);
			esito = "catalogoOK";

		}
		return esito;
	}
}
