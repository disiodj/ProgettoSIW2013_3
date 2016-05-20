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
<%
	Ordine ordine = (Ordine) session.getAttribute("ordine");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ordine Evaso</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
#form {
	border: 2px;
	background-color: rgba(255, 255, 0, 0.0);
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

	<br>
	<br>
	<h1 align="center">
		<font color="white"> L'ordine #<%=ordine.getIdOrdine()%> è
			stato evaso correttamente
		</font>
	</h1>
	<div id="form" align="center">
		<table border="2" class="tableStyle">
			<tr>
				<td><b>Id Ordine</b></td>
				<td><b>Id Cliente</b></td>
				<td><b>Data</b></td>
				<td><b>Stato</b></td>
			</tr>

			<tr>
				<td><%=ordine.getIdOrdine()%></td>
				<td><%=ordine.getCliente().getIdUtente()%>
				<td><%=ordine.getData()%></td>
				<td><%=ordine.getStato()%></td>
			</tr>
		</table>
	</div>
	<br>

	<p align="center">
		<A href="paginaAmministratore.jsp" class="classname">Torna alla
			HOME Amministratore</A> <br /> <br> <a href="paginaBenvenuto.jsp"
			class="classname"> Torna alla HOME </a>
	</p>
</body>
</html>