package it.uniroma3.action;
import it.uniroma3.model.Facade;
import it.uniroma3.model.FacadeImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public abstract class Action {
//Prova_2
	Facade facade = FacadeImpl.getInstance();

	public abstract String esegui(HttpServletRequest request)
			throws ServletException;
}