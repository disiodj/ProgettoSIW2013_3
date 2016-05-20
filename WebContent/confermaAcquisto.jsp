<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
%>
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
		.getRequestDispatcher("/erroreLogin.jsp");
		rd.forward(request, response);
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sei Sicuro?</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
input,textarea { 
border: 1px solid #FF0000;
}

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
	<h1 align="center">Sei sicuro di voler terminare l'acquisto?</h1>
	<br>
	<br>
	<h2 align="center">
		<font color="FFFFFF"> Riepilogo ordine:</font>
	</h2>
	<%
		Ordine ordine = (Ordine)session.getAttribute("ordine");
		Map<String,RigaOrdine> carrello = ordine.getRigheOrdine();
	%>

	<div id="form" align="center">
		<table border="2" class="tableStyle">
			<tr>
				<td><b>Codice</b></td>
				<td><b>Nome</b></td>
				<td><b>Quantità</b></td>
				<td><b>Descrizione</b></td>
				<td><b>Prezzo Riga</b></td>
			</tr>

			<%
				for (RigaOrdine riga : carrello.values() ) {
			%>

			<tr>
				<td><%=riga.getProdotto().getCodiceProdotto()%></td>
				<td><%=riga.getProdotto().getNome()%></td>
				<td><%=riga.getQuantita()%></td>
				<td><%=riga.getProdotto().getDescrizione()%></td>
				<td><%=riga.getProdotto().getPrezzo() * riga.getQuantita()%>
					EUR</td>


			</tr>
			<%
				}
			%>
			<tr>
				<td colspan='4'><b>TOTALE</b></td>
				<td><b>
						<%
							double tot = 0; 	for (RigaOrdine riga : carrello.values())
								tot+= riga.getProdotto().getPrezzo() * riga.getQuantita();
						%> <%=tot%>
						EUR
				</b></td>



			</tr>
		</table>
		<br> <br />

		<form action="confermaAcquisto.do" method="post">
			<input type="submit" name="si/no" value="Si" /> <input type="submit"
				name="si/no" value="No" />
		</form>
	</div>

	<br />
	<p align="center">
		<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a>
	</p>


</body>
</html>