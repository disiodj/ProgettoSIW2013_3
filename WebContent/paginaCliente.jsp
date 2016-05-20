<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Cliente cliente = (Cliente) session.getAttribute("utente");
	boolean autorizzato = true;
	if (cliente != null)
		autorizzato &= (cliente.getRole().equals("user"));
	else
		autorizzato = false;
	if (!autorizzato) {
		out.clear();
		RequestDispatcher rd = application
				.getRequestDispatcher("erroreLogin.jsp");
		rd.forward(request, response);
		return;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina Cliente</title>
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
		<%=cliente.getNome()%>
		nella tua HOME <span></span>
	</h1>
	<div id="form" align="center">
		<h2>Scegli quale operazione vuoi effettuare:</h2>

		<a href="nuovoOrdine.do" class="classname"> Nuovo Ordine </a> <br>
		<br> <a href="riepilogoOrdini.do" class="classname">
			Visualizza i tuoi ordini</a>
	</div>

	<!--  
	<form action="nuovoOrdine.do" method="post">
		<input type="submit" name="scelta" value="Nuovo Ordine"> <br /> <br />
	</form>

	<form action="visualizzaOrdini.do" method="post">
		<input type="submit" name="scelta" value="Visualizza i tuoi ordini">
		</form>

-->
	<p align="center">
	<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a></p><br><br>
<div align="center">
</div>
</body>
</html>