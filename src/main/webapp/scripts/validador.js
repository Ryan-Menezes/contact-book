document.querySelector('#form').addEventListener('submit', function (e) {
	const nome = this.nome; // frmContato.nome
	const fone = this.fone; // frmContato.fone
	
	if (!nome.value) {
		alert("Preencha o campo nome");
		nome.focus();
		return e.preventDefault();
	}
	
	if (!fone.value) {
		alert("Preencha o campo fone");
		fone.focus();
		return e.preventDefault();
	}
	
	// document.forms['frmContato'].submit();
});