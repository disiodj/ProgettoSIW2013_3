<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="it.uniroma3.model.*"%>
<%@ page import="java.util.*"%>
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
<title>Effettua nuovo ordine</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
#form	{
	border: 2px;
	background-color: rgba(255,255,0,0.0);
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
	<h1 align="center">Catalogo dei prodotti disponibili<span></span></h1>
	
	<%
		List<Prodotto> prodotti =  (List<Prodotto>)session.getAttribute("prodotti");
	%>


<div align="center" id="form">
	<table border="2" class="tableStyle">
		<tr>
			<td><b>Codice</b></td>
			<td><b>Nome</b></td>
			<td><b>Prezzo Unitario</b></td>
			<td><b>Qnt - Operazione</b></td>
		</tr>

		<%
			for (Prodotto prodotto: prodotti) {
		%>

		<tr>
			<td><%=prodotto.getCodiceProdotto()%></td>
			<td><%=prodotto.getNome()%></td>
			<td><%=prodotto.getPrezzo()%></td>
			<td>
				<form action="aggiungiAlCarrello.do" method="post">
					<select name="quantita">
						<option value="1" selected="selected">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
										
					</select>
							
					<button class="classname_piccolo" type="submit" name="codProd"
						value="<%=prodotto.getCodiceProdotto()%>">Aggiungi</button>
					</form>
			</td>
			<%
				}
			%>
		</tr>
	</table>
	</div>
	<p align="center">
	<A href="confermaAnnullaAcquisto.jsp" class="classname">ANNULLA l'ordine</A>

	
</body>
</html>