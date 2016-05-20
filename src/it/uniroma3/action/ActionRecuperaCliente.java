package it.uniroma3.action;

import it.uniroma3.model.Cliente;
import it.uniroma3.model.Ordine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionRecuperaCliente extends Action {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession session = request.getSession();

		String esito = "KOidCli";
		Long idOrdine;

		try {

			idOrdine = Long.parseLong(request.getParameter("IdOrdine"));
			Ordine ordine = facade.getOrdine(idOrdine);
			if (ordine != null) {
				Cliente cliente = ordine.getCliente();
				session.setAttribute("utenteRec", cliente);
				esito = "OKidCli";

			} else
				request.setAttribute("erroreId", "L'ID inserito non esiste");

		} catch (NumberFormatException e) {
			request.setAttribute("erroreId", "Devi inserire un ID numerico");

		}

		return esito;

	}
}
