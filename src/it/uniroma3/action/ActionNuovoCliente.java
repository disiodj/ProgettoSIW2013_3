package it.uniroma3.action;

import it.uniroma3.helper.HelperNuovoCliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionNuovoCliente extends Action {

	public ActionNuovoCliente() {

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String campiValidi = "campiValidiCliente";
		HelperNuovoCliente helper = new HelperNuovoCliente(request);

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String indirizzo = request.getParameter("indirizzo");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		request.setAttribute("nome", nome);
		request.setAttribute("cognome", cognome);
		request.setAttribute("indirizzo", indirizzo);
		request.setAttribute("email", email);
		request.setAttribute("username", username);
		request.setAttribute("password", password);

		if (helper.convalida()) {

			session.setAttribute("nome", nome);
			session.setAttribute("cognome", cognome);
			session.setAttribute("email", email);
			session.setAttribute("indirizzo", indirizzo);
			session.setAttribute("username", username);
			session.setAttribute("password", password);
		} else {
			campiValidi = "campiNONValidiCliente";
			request.setAttribute("errore", "Attenzione,valori non validi");
		}

		return campiValidi;
	}
}
