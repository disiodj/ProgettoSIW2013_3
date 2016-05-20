<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="it.uniroma3.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%
	Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>utenteLoggato</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
	<br>
	<br>
	<br>
	<h1 align="center">
		ATTENZIONE! Hai già effettuato il login come: <br>
		<%=utente.getRole()%>:
		<%=utente.getNome()%>
		<%=utente.getCognome()%></h1>
	<br>
	<br>

	<div align="center">
		<form action="logout.do" method="get">
			<input type="submit" name="logout" value="Logout" />
		</form>
	</div>
	<br />
	<br>
	<br>

	<p align="center">
		<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a>
	</p>


</body>
</html>