<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Amministratore amministratore = (Amministratore) session
			.getAttribute("utente");
	boolean autorizzato = true;
	if (amministratore != null)
		autorizzato &= (amministratore.getRole().equals("admin"));
	else
		autorizzato = false;
	if (!autorizzato) {
		out.clear();
		RequestDispatcher rd = application
				.getRequestDispatcher("/erroreLogin.jsp");
		rd.forward(request, response);
		return;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina Amministratore</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
	<div align="right">
		Login:
		<%=utente.getRole()%>
		-
		<%=utente.getNome()%>
		<%=utente.getCognome()%>
		<form action="logout.do" method="get">
			<input type="submit" name="logout" value="Logout" />
		</form>
	</div>
	<br>
	<br>
	<br>
	<h1 align="center">
		Benvenuto
		<%=amministratore.getNome() %>
		nella tua HOME<span></span>
	</h1>
	<div id="form" align="center">
		<h2>Scegli quale operazione vuoi effettuare:</h2>

		<A HREF="nuovoProdotto.jsp" class="classname"> Aggiungi Prodotti </A>
		<br>
		<br>
		<A HREF="consultaOrdini.do" class="classname"> Consulta ordini chiusi </A>
		<br>
		<br>
		<br> <A HREF="recuperaDatiCliente.jsp" class="classname">
			Recupera i dettagli di un cliente a partire da un ordine </A><br />
		<br>
		<br> <A HREF="evasioneOrdine.jsp" class="classname"> Evadi un
			ordine </A>
	</div>
	<p align="center">
	<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a></p><br><br>

</body>
</html>