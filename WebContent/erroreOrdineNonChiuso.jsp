<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
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
<%Ordine ordine = (Ordine)session.getAttribute("ordine"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attenzione</title>
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
	<h1 align="center">Risultato della ricerca dell'ordine da evadere:<span></span></h1>
	<br><br>
	<h1 align="center"><font color="white">
		Errore sull'evasione dell'ordine<span></span></font>
	</h1>
	<br>
	<br>
<h1 align="center"><font color="white">L'ordine #<%=ordine.getIdOrdine() %> non è stato evaso.
<br>Motivo:
<%
if(ordine.getStato().equals("aperto"))	{
	 %>
	 Ordine ancora aperto
	 <% } %><%
else if (ordine.getStato().equals("evaso"))	{
%>
Ordine già evaso
	 <% } %>

</font></h1>
<br>
<p align="center">
<A href="evasioneOrdine.jsp" class="classname"> Torna indietro </A></p>
<br><br><br><br><br>
	
<p align="center">
		<A href="paginaAmministratore.jsp" class="classname">Torna alla
			HOME Amministratore</A> <br /> <br> <a href="paginaBenvenuto.jsp"
			class="classname"> Torna alla HOME </a>
	</p>
</body>
</html>