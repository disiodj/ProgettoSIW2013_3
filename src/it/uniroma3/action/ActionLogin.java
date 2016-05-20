package it.uniroma3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.uniroma3.model.Utente;

public class ActionLogin extends Action{

	public ActionLogin() {

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();


		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String esito = "KOUtente";

		Utente utente = facade.autentica(username, password);
		if (utente != null) {
			session.setAttribute("utente", utente);

			if (utente.getRole().equals("user"))
				esito = "OKCliente";
			else
				esito = "OKAdmin";
		}

		return esito;
	}

}
