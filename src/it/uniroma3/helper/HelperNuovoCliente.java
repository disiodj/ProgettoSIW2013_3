package it.uniroma3.helper;

import javax.servlet.http.HttpServletRequest;

public class HelperNuovoCliente {
	protected HttpServletRequest request;
	

	public HelperNuovoCliente(HttpServletRequest request) {
		this.request = request;

	}

	public boolean convalida() {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String indirizzo = request.getParameter("indirizzo");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordR = request.getParameter("passwordR");

		
		

		boolean campiValidi = true;
		if (nome == null || nome.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreNome", "inserisci un nome");
		}

		if (cognome == null || cognome.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreCognome", "inserisci un cognome");
		}

		if (indirizzo == null || indirizzo.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreIndirizzo", "inserisci un indirizzo");
		}
		if (email == null || nome.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreEmail", "inserisci una email");
		}
		if (username == null || username.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreUsername", "inserisci una username");
		}

		if (password == null || password.isEmpty()) {
			request.setAttribute("errorePassword", "inserisci una password");
			campiValidi = false;

			/* Controllo lunghezza minima password */
		} else if (password.length() < 5) {
			campiValidi = false;
			request.setAttribute("errorePassword",
					"la password deve contenere almeno 5 caratteri");
		}

		/* Controllo Ripeti Password */
		else if (!password.equals(passwordR)) {
			campiValidi = false;
			request.setAttribute("errorePasswordR",
					"le password non coincidono");
		}
		return campiValidi;
	}

}
