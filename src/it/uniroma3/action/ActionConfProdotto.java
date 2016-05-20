package it.uniroma3.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionConfProdotto extends Action {

	public String esegui(HttpServletRequest request) {

		HttpSession session = request.getSession();

		String risposta = request.getParameter("si/no");
		String esito;

		if (risposta.equals("NO")) {
			request.setAttribute("riprova", "Ripeti la registrazione del prodotto");
			esito = "prodottoNONconf";
		} else {

		
			String nome = (String) session.getAttribute("nomeP");
			String descrizione = (String) session.getAttribute("descrizione");
			Double prezzo =  Double.parseDouble((String)session.getAttribute("prezzoP"));
			String codiceProdotto = (String) session.getAttribute("codice");
			

			if (facade.aggiungiProdotto(nome,descrizione,prezzo, codiceProdotto))
				esito = "prodottoInserito";
			else{
				esito = "erroreInserimento";
				request.setAttribute("errore", "CODICE gia presente");
			}

		}
		return esito;
	}
}
