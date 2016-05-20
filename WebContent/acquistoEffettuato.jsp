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
	<h1 align="center">Complimenti hai aggiunto questo prodotto nel
		Carrello</h1>

	<br>
	<h2 align="center">
		<font color="FFFFFF"> Ordine:</font>
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
				<td>Prezzo Riga</td>
			</tr>

			<%
				for (RigaOrdine riga : carrello.values()) {
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
							int tot = 0;
							for (RigaOrdine riga : carrello.values())
								tot += riga.getProdotto().getPrezzo() * riga.getQuantita();
						%> <%=tot%>
						euro
				</b></td>



			</tr>
		</table>
	</div>

	<p align="center">
		<A href="paginaCliente.jsp" class="classname">Torna alla HOME
			Cliente</A> <br /><br>
		<br> <a href="paginaBenvenuto.jsp" class="classname"> Torna
			alla HOME </a>
	</p>

</body>
</html>