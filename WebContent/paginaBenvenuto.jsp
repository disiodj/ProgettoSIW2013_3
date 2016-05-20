<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="it.uniroma3.model.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Benvenuto!</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
	<div align="right">
		Login:
		<%
		if (session.getAttribute("utente") != null) {
	%>
		<%=utente.getRole()%>
		-
		<%=utente.getNome()%>
		<%=utente.getCognome()%>
		<form action="logout.do" method="get">
			<input type="submit" name="logout" value="Logout" />
		</form>
		<%
			} else {
		%>
		nessuno
		<%
			}
		%>

	</div>
	<br>
	<br>
	<br>
	<h1 align="center">
		Sistema di un negozio online per la vendita e gestione di prodotti<span></span>
	</h1>
	<br>
	<div id="banner"></div>
	<p align="center">
		<a href="login.jsp" class='classname'> Login/Entra nel sistema </A> <br>
		<br> <a href="nuovoCliente.jsp" class='classname'> Effettua
			NUOVA REGISTRAZIONE </A> <br> <br> <a
			href="visualizzaCatalogo.do" class='classname'> Visualizza
			CATALOGO </A> <br> <br>
	</p>

</body>
</html>