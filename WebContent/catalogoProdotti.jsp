<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.*"%>
<%@ page import="it.uniroma3.model.*"%>
<%
	Utente utente = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Catalogo dei prodotti</title>
<link rel="stylesheet" type="text/css" href='media/stile.css'>
<style>
#form {
	border: 2px;
	background-color: rgba(255, 255, 0, 0.0);
}
</style>
</head>

<body>
	<div align="right">
		Login:
		<%
		if (session.getAttribute("utente")!= null)	{
	%>
		<%=utente.getRole()%>
		-
		<%=utente.getNome()%>
		<%=utente.getCognome()%>
		<form action="logout.do" method="get">
			<input type="submit" name="logout" value="Logout" />
		</form>
		<%
			}else {
		%>
		nessuno
		<%
			}
		%>

	</div>
	<br>
	<br>
	<br>
	<h1 align="center">
		Catalogo dei prodotti disponibili<span></span>
	</h1>
	<%
		List<Prodotto> prodotti = (ArrayList<Prodotto>) session
		.getAttribute("prodotti");
	%>


	<div id="form" align="center">
		<table border="2" class="tableStyle">
			<tr>
				<td><b>Codice</b></td>
				<td><b>Nome</b></td>
				<td><b>Operazione</b></td>
			</tr>

			<%
				for (Prodotto prodotto : prodotti) {
			%>

			<tr>
				<td><%=prodotto.getCodiceProdotto()%></td>
				<td><%=prodotto.getNome()%></td>
				<td>
					<form action="infoProdotto.do" method="post">
						<button type="submit" name="codProd" class="classname_piccolo"
							value="<%=prodotto.getCodiceProdotto()%>">Visualizza</button>
					</form>
				</td>
				<%
					}
				%>
			</tr>
		</table>
	</div>

	<p align="center">
		<a href="paginaBenvenuto.jsp" class="classname"> Torna alla HOME </a>
	</p>
</body>
</html>
