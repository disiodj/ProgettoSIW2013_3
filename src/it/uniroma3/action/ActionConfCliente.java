package it.uniroma3.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionConfCliente extends Action{

	public ActionConfCliente(){

	}

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();

		String risposta = request.getParameter("si/no");
		String esito;

		if (risposta.equals("NO")) {
			request.setAttribute("riprova", "Ripeti la registrazione");
			esito = "clienteNONconf";
		} else {

	
			String nome = (String) session.getAttribute("nome");
			String cognome = (String) session.getAttribute("cognome");
			String email = (String) session.getAttribute("email");
			String indirizzo = (String) session.getAttribute("indirizzo");
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");

			if (facade.registraCliente(nome, cognome, indirizzo, email, username,
					password))
				esito = "clienteRegistrato";
			else{
				esito = "erroreRegistrazione";
				request.setAttribute("erroreChiave", "USERNAME gia utilizzata");
			}

		}
		return esito;
	}
}
