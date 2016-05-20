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
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dettagli Cliente</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
#form {
	border: 2px;
	background-color: rgba(255, 255, 0, 0.0);
	width: 700px;
}
</style>
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
		Dettagli cliente<span></span>
	</h1>
	<%
		Cliente cliente = (Cliente) session.getAttribute("utenteRec");
	%>
	<br>
	<br>
	<h2 align="center">
		ID Cliente:
		<%=cliente.getIdUtente()%></h2>

	<div id="form" align="center">
		<table border="2" class="tableStyle">
			<tr>
				<td><b>Id</b></td>
				<td><b>Nome</b></td>
				<td><b>Cognome</b></td>
				<td><b>Username</b></td>
				<td><b>Indirizzo</b></td>
				<td><b>E-mail</b></td>

			</tr>

			<tr>
				<td><%=cliente.getIdUtente()%></td>
				<td><%=cliente.getNome()%></td>
				<td><%=cliente.getCognome()%></td>
				<td><%=cliente.getUsername()%></td>
				<td><%=cliente.getIndirizzo()%></td>
				<td><%=cliente.getEmail()%></td>
			</tr>

		</table>
	</div>
	<br>
	<br>
	<p align="center">

		<A href="paginaAmministratore.jsp" class="classname">Torna alla
			HOME Amministratore</A> <br /> <br> <a href="paginaBenvenuto.jsp"
			class="classname"> Torna alla HOME </a>
	</p>
</body>
</html>