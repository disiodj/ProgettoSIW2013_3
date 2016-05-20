<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="it.uniroma3.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Info Prodotto</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
</head>
<body>
		<br>
	<br>
	<br>
	<h1 align="center">Informazioni sul prodotto scelto<span></span></h1>
	<br />

	<%
		Prodotto p = (Prodotto) request.getAttribute("prodotto");
	%>
<div id="form" align="center">
	<b>CodiceProdotto</b>: <%=p.getCodiceProdotto()%><br /> 
	<b>Nome</b>: <%=p.getNome()%><br /> 
	<b>Descrizione</b>: <%=p.getDescrizione()%><br /> 
	<b>Prezzo</b>: <%=p.getPrezzo()%><br /> 
	<b>ID</b>: <%=p.getIdProdotto()%><br />
	</div>
	<br />
<p align="center">
	<A HREF="catalogoProdotti.jsp" class="classname"> Torna al catalogo </A><br><br>
	<A HREF="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </A>
	</p>
</body>
</html>