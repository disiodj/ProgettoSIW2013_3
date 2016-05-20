<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


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
				.getRequestDispatcher("erroreLogin.jsp");
		rd.forward(request, response);
		return;
	}
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserimento prodotti</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
input,textarea {
	width: 200px;
}
</style>
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
		Inserisci i dati del prodotto da aggiungere<span></span>
	</h1>
	<br>
	<h3 align="center"><font color="FF6600"> <%=(request.getAttribute("errore") != null) ? request
					.getAttribute("errore") : ""%></font></h3>
	<h2 align="center">
		<font color="red"> <%=(request.getAttribute("erroreChiave") != null) ? request
					.getAttribute("erroreChiave") : ""%>
		</font>
	</h2>
		<div id="form" align="center">
		<form action="inserimentoProdotto.do" method="post">
			<b>Nome</b> <br> <input type="text" name="nomeP"
				value="<%=((request.getAttribute("nomeP") != null)) ? request
					.getAttribute("nomeP") : ""%>" />

			<%=(request.getAttribute("erroreNomeP") != null) ? request
					.getAttribute("erroreNomeP") : ""%>

			<br /> <br> <b>Codice Prodotto</b> <br> <input type="text"
				name="codice"
				value="<%=((request.getAttribute("codice") != null)) ? request
					.getAttribute("codice") : ""%>" />

			<%=(request.getAttribute("erroreCodice") != null) ? request
					.getAttribute("erroreCodice") : ""%>

			<br /> <br> <b>Descrizione</b><br> <input type="text"
				name="descrizione"
				value="<%=((request.getAttribute("descrizione") != null)) ? request
					.getAttribute("descrizione") : ""%>" />

			<%=(request.getAttribute("erroreDescrizione") != null) ? request
					.getAttribute("erroreDescrizione") : ""%>

			<br /> <br> <b>Prezzo</b><br> <input type="text"
				name="prezzoP"
				value="<%=((request.getAttribute("prezzoP") != null)) ? request
					.getAttribute("prezzoP") : ""%>" />


			<%=(request.getAttribute("errorePrezzo") != null) ? request
					.getAttribute("errorePrezzo") : ""%>


			<br /> <br /> <br> <input type="submit" name="submit"
				value="AGGIUNGI" />

		</form>
	</div>

	<br />
	<p align="center">

		<A href="paginaAmministratore.jsp" class="classname">Torna alla
			HOME Amministratore</A> <br /> <br> <a href="paginaBenvenuto.jsp"
			class="classname"> Torna alla HOME </a>
	</p>

</body>



</html>