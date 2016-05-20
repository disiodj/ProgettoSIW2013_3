<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
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
		.getRequestDispatcher("erroreLogin.jsp");
		rd.forward(request, response);
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prodotto aggiunto all'ordine!!</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
#form {
	border: 2px;
	background-color: rgba(255, 255, 0, 0.0);
}
button,textarea {
	color: #000000;
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
		<%
			if (request.getAttribute("prodotto") != null) {
		%>
		<%="Complimenti hai aggiunto questo prodotto nel Carrello"%><span></span>
	</h1>
	<h1 align="center">
		<font color="FFFFFF"> <%
 	Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
 %> <%=prodotto.getNome()%></font>
	</h1>
	<%
		}
	%>


	<br>
	<h2 align="center">
		<font color="FFFFFF"> Riepilogo ordine:</font>
	</h2>
	<%
		Ordine ordine = (Ordine) session.getAttribute("ordine");
			Map<String, RigaOrdine> carrello = ordine.getRigheOrdine();
	%>
	<div id="form" align="center">
		<table border="2" class="tableStyle">
			<tr>
				<td>Codice</td>
				<td>Nome</td>
				<td>Quantità</td>
				<td>Descrizione</td>
				<td>Prezzo</td>
			</tr>

			<%
				for (RigaOrdine riga : carrello.values() ) {
			%>

			<tr>
				<td><%=riga.getProdotto().getCodiceProdotto()%></td>
				<td><%=riga.getProdotto().getNome()%></td>
				<td>
					<form action="aggiornaQuantita.do" method="post">
						<input type="text" name="quantita" value="<%=riga.getQuantita()%>" /><br><br>
						<button type="submit" name="codProd" value="<%=riga.getProdotto().getCodiceProdotto()%>">Aggiorna</button>
					</form>
				</td>
				<td><%=riga.getProdotto().getDescrizione()%></td>
				<td><%=riga.getPrezzo()%> EUR</td>

			</tr>
			<%
				}
			%>
			<tr>
				<td colspan='4'><b>TOTALE</b></td>
				<td><b> <%
 	double tot = 0; 	for (RigaOrdine riga : carrello.values())
        						tot+= riga.getPrezzo();
 %> <%=tot%> EUR
				</b></td>



			</tr>
		</table>
	</div>

	<p align="center">
		<A HREF="nuovoOrdine.jsp" class="classname"> Continua ad
			acquistare </A>
	</p>

	<br />
	<br />

	<p align="center">
		<A HREF="confermaAcquisto.jsp" class="classname"> Termina Acquisto</A>
<A HREF="confermaAnnullaAcquisto.jsp" class="classname">
			Annulla Acquisto</A>
	</p>



</body>
</html>