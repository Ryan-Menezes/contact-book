<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans" %>
<% JavaBeans contato = (JavaBeans) request.getAttribute("contato"); %>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
	<title>Agenda de contatos</title>
	<link rel="icon" href="imagens/favicon.png" />
	<link rel="stylesheet" href="style.css" />
</head>
<body>
	<h1>Editar contato</h1>
	
	<form name="frmContato" action="update" id="form">
		<table>
			<tr>
				<td><input type="text" name="id" placeholder="Id" class="input-box" value="<%= contato.getId() %>" readonly /></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" placeholder="Nome" class="input-box" value="<%= contato.getNome() %>" /></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" placeholder="Fone" class="input-box" value="<%= contato.getFone() %>" /></td>
			</tr>
			<tr>
				<td><input type="email" name="email" placeholder="E-mail" class="input-box" value="<%= contato.getEmail() %>" /></td>
			</tr>
		</table>
		
		<input type="submit" value="Salvar" class="btn-primary" />
	</form>
	
	<script src="scripts/validador.js"></script>
</body>
</html>