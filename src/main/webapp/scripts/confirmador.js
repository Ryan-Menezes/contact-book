function confirmar(id) {
	const resposta = confirm("Confirma a exclusão deste contato?");
	
	if (resposta) {
		window.location.href = 'delete?id=' + id;
	}
}