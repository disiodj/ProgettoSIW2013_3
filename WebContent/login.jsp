<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	boolean autorizzato = true;
	RequestDispatcher rd;
	Utente utente = (Utente) session.getAttribute("utente");
	if (session.getAttribute("utente") == null)
		autorizzato = true;
	else
		autorizzato = false;

	if (!autorizzato) {
		if (session.getAttribute("utente") != null) {
			if (utente.getRole().equals("user"))	{
				rd = application.getRequestDispatcher("/paginaCliente.jsp");
				rd.forward(request, response);
			}
			else if (utente.getRole().equals("admin"))	{
				rd = application.getRequestDispatcher("/paginaAmministratore.jsp");
				rd.forward(request, response);
			}
		} else {
			out.clear();
			rd = application.getRequestDispatcher("/erroreLogin.jsp");
			rd.forward(request, response);
			return;
		}
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
	<br>
	<br>
	<br>
	<h1 align="center">
		Esegui l'accesso al sistema:<span></span>
	</h1>
	<br>
	<form action="login.do" method="post">
		<div id="form" align="center">
			<i><b>Username</b></i>:<br>
			<input type="text" name="username" /> <br>
			<br> <i><b>Password</b></i>:<br>
			<input type="password" name="password" /> <br /> <br> <input
				type="submit" value="Login" name="Login" />
		</div>
	</form>
	<br>
	<br />
	<p align="center">
		<a href="paginaBenvenuto.jsp" class="classname">TORNA ALLA HOME</a>
	</p>
</body>
</html>