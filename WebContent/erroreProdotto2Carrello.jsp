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
<h1> Impossibile aggiungere il prodotto seguente:</h1>

<%
		Prodotto p = (Prodotto) request.getAttribute("prodotto");
	%>
	CodiceProdotto: <%=p.getCodiceProdotto()%><br /> 
	Nome: <%=p.getNome()%><br /> 
	Descrizione: <%=p.getDescrizione()%><br /> 
	Prezzo: <%=p.getPrezzo()%><br /> 
	ID: <%=p.getIdProdotto()%><br />
	<br />
	
	<A HREF="paginaNuovoOrdine.jsp"> Riprova </A>
	<A HREF="paginaCliente.jsp"> Torna alla HOME CLIENTE </A>


</body>
</html>