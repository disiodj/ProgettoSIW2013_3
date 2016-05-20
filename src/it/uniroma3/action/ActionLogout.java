package it.uniroma3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionLogout extends Action{

	public ActionLogout() {

	}

	public String esegui(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("utente");
		String logout = "logout";
		
		
		return logout;
	}

}
