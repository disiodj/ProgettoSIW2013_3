<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="it.uniroma3.model.*"%>
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
<title>Errore Interno!</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
<br><br><br>
<h1 align="center"> Impossibile concludere questo ordine:</h1>
<br>
<h1 align="center"> Questo ordine è gia stato concluso</h1>

<br><br>
	<p align="center">	
	<A HREF="nuovoOrdine.jsp" class="classname"> Fai un nuovo ordine </A><br><br>
	<A HREF="paginaCliente.jsp" class="classname"> Torna alla HOME CLIENTE </A>
</p>
</body>
</html>