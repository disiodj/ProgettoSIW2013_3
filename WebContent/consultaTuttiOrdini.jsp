<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.uniroma3.model.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
%>

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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Catalogo dei prodotti</title>
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
		<%
		if (session.getAttribute("utente")!= null)	{
	%>
		<%=utente.getRole()%>
		-
		<%=utente.getNome()%>
		<%=utente.getCognome()%>
		<form action="logout.do" method="get">
			<input type="submit" name="logout" value="Logout" />
		</form>
		<%
			}else {
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
		Lista degli ordini chiusi in attesa di evasione:<span></span>
	</h1>
	<%
		List<Ordine> ordini = (ArrayList<Ordine>) session
			.getAttribute("ordini");
	%>


	<div id="form" align="center">
		<table border="2" class="tableStyle">
			<tr>
				<td><b>Id ordine</b></td>
				<td><b>Data</b></td>
				<td><b>Id Cliente</b></td>
			</tr>

			<%
				for (Ordine ordine: ordini) {
			%>

			<tr>
				<td><%=ordine.getIdOrdine()%></td>
				<td><%=ordine.getData()%></td>
				<%
					Cliente cliente = ordine.getCliente();
				%>
				<td><%=cliente.getIdUtente()%></td>

				<%
					}
				%>
			</tr>
		</table>
	</div>
	<p align="center">
		<a href="paginaAmministratore.jsp" class="classname"> Torna alla
			HOME Amministratore</a>
	</p>
	<br>
	<p align="center">
		<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a>
	</p>
</body>
</html>
