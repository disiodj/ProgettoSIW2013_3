<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina di Conferma Registrazione</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
	<br>
	<br>
	<br>
	<h1 align="center">Confermi la registrazione con i seguenti dati?<span></span></h1>
	<div id="form" align="center">
	<b>Nome</b>:
	<%=request.getParameter("nome")%>
	<br><br>
	<b>Cognome</b>:
	<%=request.getParameter("cognome")%>
	<br><br>
	<b>Indirizzo</b>:
	<%=request.getParameter("indirizzo")%>
	<br><br>
	<b>E-Mail</b>:
	<%=request.getParameter("email")%>
	<br><br>
	<b>Username</b>:
	<%=request.getParameter("username")%>

	<form action="confermaRegistrazione.do" method="get">

		<br /> <b><font color="red">CONFERMI DEFINITIVAMENTE?</font></b><br> <input type="submit" name="si/no" value="SI" />
		<input type="submit" name="si/no" value="NO" />

	</form>
	</div>


</body>
</html>