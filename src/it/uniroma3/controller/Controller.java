package it.uniroma3.controller;

import it.uniroma3.action.Action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* ad ogni azione corrisponde una classe che viene istanziata; viene eseguito il metodo esegui,
 * in base all'esito viene caricata la pagina di destinazione*/

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, String> comando2action;
	private Map<String, String> esito2pagina;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		/*In questo modo, le richieste inviate tramit GET vengono cmq processate da POST*/
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String destinazione = null;
		String comando = request.getServletPath();// ?

		if (comando.equals("/Controller"))
			comando = (String) request.getAttribute("path");

		String nomeAction = this.comando2action.get(comando);
		
		if (nomeAction == null) {
			destinazione = "/errore.jsp";
		} else {
			Action action = null;

			try {
				action = (Action) Class.forName(nomeAction).newInstance();
				String esitoAction = action.esegui(request);
				destinazione = this.esito2pagina.get(esitoAction);

			} catch (InstantiationException e) {
				destinazione = "/errore.jsp";
			} catch (IllegalAccessException e) {
				destinazione = "/errore.jsp";
			} catch (ClassNotFoundException e) {
				destinazione = "/errore.jsp";
				
			}
		}

		ServletContext application = getServletContext();

		RequestDispatcher rd = application.getRequestDispatcher(destinazione);
		rd.forward(request, response);
	}

	public void init() {
		
		this.comando2action = new HashMap<String, String>();
		
		this.comando2action.put("/login.do", "it.uniroma3.action.ActionLogin"); 
		
		this.comando2action.put("/register.do","it.uniroma3.action.ActionNuovoCliente"); 
		
		this.comando2action.put("/inserimentoProdotto.do","it.uniroma3.action.ActionNuovoProdotto"); 
		
		this.comando2action.put("/nuovoOrdine.do","it.uniroma3.action.ActionNuovoOrdine"); 
		
		this.comando2action.put("/riepilogoOrdini.do","it.uniroma3.action.ActionRiepilogoOrdini"); 
		
		this.comando2action.put("/confermaRegistrazione.do","it.uniroma3.action.ActionConfCliente"); 
		
		this.comando2action.put("/aggiungiAlCarrello.do","it.uniroma3.action.ActionCarrello"); 
		
		this.comando2action.put("/aggiornaQuantita.do","it.uniroma3.action.ActionAggiornaQuantita"); 
		
		this.comando2action.put("/visualizzaCatalogo.do","it.uniroma3.action.ActionVisualizzaCatalogo"); 
		
		this.comando2action.put("/infoProdotto.do","it.uniroma3.action.ActionDettaglioProdotto"); 
		
		this.comando2action.put("/confermaAcquisto.do","it.uniroma3.action.ActionConfAcquisto"); 
		
		this.comando2action.put("/annullaAcquisto.do","it.uniroma3.action.ActionAnnullaAcquisto"); 
		
		this.comando2action.put("/confermaProdotto.do","it.uniroma3.action.ActionConfProdotto"); 
		
		this.comando2action.put("/consultaOrdini.do","it.uniroma3.action.ActionConsTuttiOrdini"); 

		this.comando2action.put("/recuperaCliente.do","it.uniroma3.action.ActionRecuperaCliente"); 
		
		this.comando2action.put("/trovaOrdine.do","it.uniroma3.action.ActionTrovaOrdine"); 
		
		this.comando2action.put("/evadiOrdine.do","it.uniroma3.action.ActionEvadiOrdine"); 

		this.comando2action.put("/logout.do","it.uniroma3.action.ActionLogout"); 


		
		this.esito2pagina = new HashMap<String, String>();
	
		/*ActionLogin*/
		this.esito2pagina.put("KOUtente", "/erroreLogin.jsp");
		this.esito2pagina.put("OKCliente", "/paginaCliente.jsp");
		this.esito2pagina.put("OKAdmin", "/paginaAmministratore.jsp");
		
		/*ActionNuovoCliente*/
		this.esito2pagina.put("campiValidiCliente", "/confermaNuovoCliente.jsp");//campiValidiCliente
		this.esito2pagina.put("campiNONValidiCliente", "/nuovoCliente.jsp");
		/*ActionNuovoProdotto*/
		
		this.esito2pagina.put("campiValidiProd", "/confermaNuovoProdotto.jsp");//campiValidiProdotto
		this.esito2pagina.put("campiNONValidiProd", "/nuovoProdotto.jsp");
		
		/*ActionNuovoOrdine*/
		this.esito2pagina.put("ordineOK", "/nuovoOrdine.jsp");  
		this.esito2pagina.put("erroreOrdine", "/errore.jsp"); // da vedere,erroreScritturaDB
		
		/*ActionRiepilogoOrdini*/
		this.esito2pagina.put("visualOrdiniOK", "/riepilogoOrdini.jsp");
		this.esito2pagina.put("ErroreVisualOrdini", "/errore.jsp"); // da vedere,erroreScritturaDB
		
		/*ActionConfermaCliente*/
		this.esito2pagina.put("clienteNONconf",  "/nuovoCliente.jsp");
		this.esito2pagina.put("clienteRegistrato","/successoNuovoCliente.jsp");
		this.esito2pagina.put("erroreRegistrazione", "/nuovoCliente.jsp"); // erroreScritturaDB
		
		/*ActionCarrello*/
		this.esito2pagina.put("ErroreIns2Carrello", "/erroreProdotto2Carrello.jsp");
		this.esito2pagina.put("Ins2CarrelloOK", "/riepilogoCarrello.jsp" );
		
		/*ActionAggiornaQuantita*/
		this.esito2pagina.put("ErroreAggiorna", "/errore.jsp");
		this.esito2pagina.put("OKAggiorna", "/riepilogoCarrello.jsp" );
		
		/*ActionVisualizzaCatalogo*/
		this.esito2pagina.put( "ErroreCatalogo", "errore.jsp"); //da vedere, erroreCaricamentoDB
		this.esito2pagina.put("catalogoOK", "/catalogoProdotti.jsp");
		
		/*ActionDettaglioProdotto*/
		this.esito2pagina.put("erroreDettaglio", "errore.jsp"); //da vedere,erroreCaricamentoDB
		this.esito2pagina.put("dettaglioOK", "/dettagliProdotto.jsp");
		
		/*ActionConfermaAcquisto*/
		this.esito2pagina.put("erroreAcquisto", "/erroreAcquisto.jsp" ); //erroreCaricamentoDB	
		this.esito2pagina.put("acquistoRegistrato", "/acquistoEffettuato.jsp" );
		this.esito2pagina.put("acquistoNONconf","/riepilogoCarrello.jsp" );
		
		/*ActionAnnullaAcquisto*/
		this.esito2pagina.put("erroreAnnulla", "/errore.jsp" ); //erroreCaricamentoDB	
		this.esito2pagina.put("acquistoAnnullato", "/acquistoAnnullato.jsp" );
		this.esito2pagina.put("annullaNONconf","/riepilogoCarrello.jsp" );
		
		/*ActionConfermaProdotto*/
		this.esito2pagina.put("erroreInserimento", "/nuovoProdotto.jsp" ); //erroreCaricamentoDB	
		this.esito2pagina.put("prodottoInserito", "/successoNuovoProdotto.jsp" );
		this.esito2pagina.put("prodottoNONconf","/nuovoProdotto.jsp" );
		
		/*ActionConsTuttiOrdini*/
		this.esito2pagina.put("erroreOrdini", "/errore.jsp" );
		this.esito2pagina.put("OKOrdini","/consultaTuttiOrdini.jsp" );
		
		/*ActionRecuperaCliente*/
		this.esito2pagina.put("OKidCli", "/dettagliCliente.jsp");
		this.esito2pagina.put("KOidCli", "/recuperaDatiCliente.jsp");
		
		/*ActionTrovaOrdine*/
		this.esito2pagina.put("KOidOrd", "/evasioneOrdine.jsp");
		this.esito2pagina.put("trovato", "/ordineTrovato.jsp");
		this.esito2pagina.put("ordineNonChiuso", "/erroreOrdineNonChiuso.jsp");
		
		/*ActionEvadiOrdine*/
		this.esito2pagina.put("nonEvaso", "/errore.jsp");
		this.esito2pagina.put("evaso", "/ordineEvaso.jsp");
		this.esito2pagina.put("annullato", "/evasioneOrdine.jsp");
		
		/*ActionLogout*/
		this.esito2pagina.put("logout", "/paginaBenvenuto.jsp");

		

	
	}
}