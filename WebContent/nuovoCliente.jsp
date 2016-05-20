<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="it.uniroma3.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	boolean autorizzato = true;
	if (session.getAttribute("utente") == null)
		autorizzato = true;
	else
		autorizzato = false;

	if (!autorizzato) {
		if (session.getAttribute("utente") != null) {
			RequestDispatcher rd = application
					.getRequestDispatcher("/utenteGiaLoggato.jsp");
			rd.forward(request, response);
		} else {
			out.clear();
			RequestDispatcher rd = application
					.getRequestDispatcher("/erroreLogin.jsp");
			rd.forward(request, response);
			return;
		}
	}
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrazione</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
input,textarea {
	width: 200px;
}

body {
	background-size: 100% 100%;
}
</style>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<h1 align="center">
		Inserisci i tuoi dati per registrarti<span></span>
	</h1>
	<br>
	<h3 align="center"><font color="FF6600"><%=(request.getAttribute("erroreChiave") != null) ? request
					.getAttribute("erroreChiave") : ""%></font></h3>
	<form action="register.do" method="post">
		<div id="form" align="center">

			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("riprova") != null) ? request
					.getAttribute("riprova") : ""%></font>
			</h3>

			<i><b>Inserisci il nome</b></i><br> <input type="text"
				name="nome"
				value="<%=((request.getAttribute("nome") != null)) ? request
					.getAttribute("nome") : ""%>" />
			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("erroreNome") != null) ? request
					.getAttribute("erroreNome") : ""%></font>
			</h3>
			<br /> <br /> <i><b>Inserisci il cognome</b></i><br> <input
				type="text" name="cognome"
				value="<%=((request.getAttribute("cognome") != null)) ? request
					.getAttribute("cognome") : ""%>" />

			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("erroreCognome") != null) ? request
					.getAttribute("erroreCognome") : ""%></font>
			</h3>
			<br /> <br /> <i><b>Inserisci l'indirizzo</b></i><br> <input
				type="text" name="indirizzo"
				value="<%=((request.getAttribute("indirizzo") != null)) ? request
					.getAttribute("indirizzo") : ""%>" />

			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("erroreIndirizzo") != null) ? request
					.getAttribute("erroreIndirizzo") : ""%></font>
			</h3>
			<br /> <br /> <i><b>Inserisci l'email</b></i><br> <input
				type="text" name="email"
				value="<%=((request.getAttribute("email") != null)) ? request
					.getAttribute("email") : ""%>" />
			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("erroreEmail") != null) ? request
					.getAttribute("erroreEmail") : ""%></font>
			</h3>
			<br /> <br /> <i><b>Inserisci una username</b></i><br> <input
				type="text" name="username"
				value="<%=((request.getAttribute("username") != null)) ? request
					.getAttribute("username") : ""%>" />

			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("erroreUsername") != null) ? request
					.getAttribute("erroreUsername") : ""%></font>
			</h3>
			<br /> <br /> <i><b>Inserisci una password</b></i><br> <input
				type="password" name="password" />
			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("errorePassword") != null) ? request
					.getAttribute("errorePassword") : ""%></font>
			</h3>

			<br /> <br /> <br /> <i><b>Ripeti password</b></i><br> <input
				type="password" name="passwordR" />
			<h3 align="center">
				<font color="FF6600"><%=(request.getAttribute("errorePasswordR") != null) ? request
					.getAttribute("errorePasswordR") : ""%></font>
			</h3>

			<br /> <br> <br> <input type="submit" name="register"
				value="CONFERMA" /> <br> <br> <input type="reset"
				name="reset" value="REIMPOSTA" /> <br />
		</div>
	</form>

	<p align="center">
		<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a>
	</p>
</body>
</html>