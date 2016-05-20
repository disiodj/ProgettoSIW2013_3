<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
	Cliente cliente = (Cliente) session.getAttribute("utente");
	boolean autorizzato = true;
	if (cliente != null)
		autorizzato &= (cliente.getRole().equals("user"));
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
<title>Riepilogo Ordini</title>
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
	<h1 align="center">
		Riepilogo ordini cliente<span></span>
	</h1>
	<%
		List<Ordine> ordini =(ArrayList<Ordine>)session.getAttribute("ordini");
	%>
	<div id="form" align="center">
		<table border="2" class="tableStyle">
			<tr>
				<td>Id Ordine</td>
				<!-- manca IDcliente, necessaria o no? -->
				<td>Data</td>
				<td>Stato</td>
			</tr>

			<%
				if(ordini.isEmpty()){
			%>
			<b><%="Non hai ancora effettuato alcun ordine"%></b><br><br>


			<%
				}
					 else {
					for (Ordine ordine : ordini) {
			%>
<br><br>
			<tr>
				<td><%=ordine.getIdOrdine()%></td>
				<!-- manca IDcliente, necessaria o no? -->
				<td><%=ordine.getData()%></td>
				<td><%=ordine.getStato()%></td>


			</tr>
			<%
				}
					}
			%>
		</table>
	</div>
	<br />
	<br>
	<br />
	<br>
	<p align="center">
		<A href="paginaCliente.jsp" class="classname">Torna alla HOME
			Cliente</A> <br />
		<br> <a href="paginaBenvenuto.jsp" class="classname"> Torna
			alla HOME </a>
	</p>
</body>
</html>