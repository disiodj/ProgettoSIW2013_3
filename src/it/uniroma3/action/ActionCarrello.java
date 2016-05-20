package it.uniroma3.action;


import it.uniroma3.model.Ordine;
import it.uniroma3.model.Prodotto;
import it.uniroma3.model.RigaOrdine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionCarrello extends Action {



	public ActionCarrello() {

	}

	public String esegui(HttpServletRequest request) {


		HttpSession session = request.getSession();

		String esito = "ErroreIns2Carrello";

		String codProdotto = request.getParameter("codProd");
		int quantita = (int) Integer.parseInt(request.getParameter("quantita"));


		Prodotto prodottoIns = facade.getProdotto(codProdotto);

		if (prodottoIns != null) {
			

			Ordine ordine = (Ordine) session.getAttribute("ordine");

			if (ordine.getRigheOrdine().containsKey(codProdotto))	{
				
				RigaOrdine r = ordine.getRigheOrdine().get(codProdotto);
				
				int q = r.getQuantita() + quantita;
				r.setQuantita(q);
				r.setPrezzo(q * prodottoIns.getPrezzo());
				
			}
			
			else	{
				RigaOrdine riga = new RigaOrdine();
				riga.setQuantita(quantita);
				riga.setProdotto(prodottoIns);
				riga.setPrezzo(quantita * prodottoIns.getPrezzo());
				
				ordine.getRigheOrdine().put(codProdotto, riga);
			}
			
			request.setAttribute("prodotto", prodottoIns);
			esito = "Ins2CarrelloOK";
		}

		return esito;

	}

}
