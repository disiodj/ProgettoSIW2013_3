<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>registrazione effettuata</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
	<br>
	<br>
	<br>
	<h1 align="center">
		Complimenti,
		<%=session.getAttribute("nome")%>, sei riuscito a registrarti con
		successo!
	</h1>
	<br />
	<p align="center">
	<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a>
	<br />
	<br />
	<a href="login.jsp" class="classname"> Esegui LOGIN </a>
	</p>
</body>
</html>