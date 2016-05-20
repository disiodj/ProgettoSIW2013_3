<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
				.getRequestDispatcher("erroreLogin.jsp");
		rd.forward(request, response);
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina di conferma inserimento nuovo prodotto</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
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
	<h1 align="center">Sei sicuro di voler inserire questo prodotto?</h1>
<div id="form" align="center">
<b>Nome</b>: <%=session.getAttribute("nomeP") %> <br><br>
<b>Codice Prodotto</b>: <%=session.getAttribute("codice") %> <br><br>
<b>Descrizione</b>:  <%=session.getAttribute("descrizione") %> <br><br>
<b>Prezzo</b>: <%=session.getAttribute("prezzoP") %><br><br><br>

<form action="confermaProdotto.do" method="get">
<b><font color="red">CONFERMI DEFINITIVAMENTE?</font></b><br><br>

<input type="submit" name="si/no" value="SI"/>
<input type="submit" name="si/no" value="NO"/>
</form>
</div>
</body>
</html>