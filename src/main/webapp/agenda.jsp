<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<JavaBeans> contatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<title>Agenda de contatos</title>
	<link rel="icon" href="imagens/favicon.png" />
	<link rel="stylesheet" href="style.css" />
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="btn-primary">Novo contato</a>
	<a href="report" class="btn-danger">Relatório</a>
	
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for(JavaBeans contato : contatos) { %>
				<tr>
					<td><%= contato.getId() %></td>
					<td><%= contato.getNome() %></td>
					<td><%= contato.getFone() %></td>
					<td><%= contato.getEmail() %></td>
					<td>
						<a href="edit?id=<%= contato.getId() %>" class="btn-primary">Editar</a>
						<a href="javascript: confirmar(<%= contato.getId() %>)" class="btn-danger">Deletar</a>
					</td>
				</tr>
			<% } %>
		</tbody>
	</table>
	
	<script src="scripts/confirmador.js"></script>
</body>
</html>