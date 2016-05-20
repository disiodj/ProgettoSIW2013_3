package it.uniroma3.helper;

import javax.servlet.http.HttpServletRequest;

public class HelperNuovoProdotto {
	protected HttpServletRequest request;

	public HelperNuovoProdotto(HttpServletRequest request) {
		this.request = request;

	}

	public boolean convalida() {
		
		String nome = request.getParameter("nomeP");
		String codice = request.getParameter("codice");
		String descrizione = request.getParameter("descrizione");
		String prezzo = request.getParameter("prezzoP");

		boolean campiValidi = true;

		if (nome == null || nome.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreNome", "inserisci un nome");
		}

		if (codice == null || codice.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreCodice", "inserisci un codice");
		}

		if (descrizione == null || descrizione.isEmpty()) {
			campiValidi = false;
			request.setAttribute("erroreDescrizione",
					"inserisci una descrizione");
		}

		try {
			double p = Double.parseDouble(prezzo);
			if (prezzo == null || prezzo.isEmpty() || p < 0 || p > 100000) {
				campiValidi = false;
				request.setAttribute("errorePrezzo", "inserisci un prezzo");
			}
		} catch (NumberFormatException e) {
			campiValidi = false;
			request.setAttribute("errorePrezzo", "inserisci un prezzo valido");
			e.printStackTrace();
		}
		
		return campiValidi;

	}

}
