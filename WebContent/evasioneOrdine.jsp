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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Evasione Ordine</title>
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
	<h1 align="center">
		Evasione ordini<span></span>
	</h1>
	<h2 align="center">
		<font color="FFFFFF">Inserire il numero d'ordine da evadere</font>
	</h2>
	
		<h3 align="center"><font color="FF6600"><%=(request.getAttribute("erroreId") != null) ? request
					.getAttribute("erroreId") : ""%></font> </h3>
					
	<div align="center" id="form">
		<form action="trovaOrdine.do" method="get">
			<b>Id Ordine</b><br> <input type="text" name="idOrdine" /><br>
			<br> <b> <input type="submit" name="cercaOrdine"
				value="Cerca" /></b>
		</form>
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