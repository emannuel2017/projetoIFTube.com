
alert('oi')
$(window).scroll(function() {

	if ($(window).scrollTop() > 44) {
		$('.main-menu').addClass("active");
		$('header').addClass("active");
	}
	else{
		$('.main-menu').removeClass("active");
		$('header').removeClass("active");
	}
});

//if (screen.width > 1024) {
//if ( $('.portipolio-sec').length > 0 ) {

//AOS.init({
//easing: 'ease-in-out-sine'
//});
//}
//}

if ( $('.videos-sec').length > 0 ) {
	$.wmBox();
}

function get(){
	fetch("/canal").then(function(response){
			console.log("ok");
		});
	}
get();

function save(){
	let xhr = new XMLHttpRequest;

	xhr.open('POST','/canal');

	xhr.setRequestHeader('Content-Type', 'application/json');



	let nome = document.getElementById('nome-cadastro').value;
	let email = document.getElementById('email-cadastro').value;
	let senha = document.getElementById('senha-cadastro').value;

	let novo = {
			"nome": `${nome}`,
			"email":`${email}`,
			"senha":`${senha}`
	};

	xhr.onload = function(){

		if(this.status == 200){
			esconderDivCadastro();
			buscarCanalPorEmail();
		}
	}

	xhr.onerro = () => alert('ERRO');
	xhr.send(JSON.stringify(novo));
	console.log('Sucesso salvo');


}

function esconderDivPerfil(){
	let div = document.getElementById('info-canal');
	div.style.display = "none";
	div.style.visibility = "hidden";
    div = document.getElementById('cadastroCanal');
	div.style.display = "none";
	div.style.visibility = "hidden";
}

function esconderDivCadastro(){
	let div = document.getElementById('cadastroCanal');
	div.style.display = "none";
	div.style.visibility = "hidden";

	div = document.getElementById('info-canal');
	div.style.display = "block";
	div.style.visibility = "visible";
}

function mostarFormularioDeCadastro(){
	let div = document.getElementById('cadastroCanal');
	div.style.display = "block";
	div.style.visibility = "visible";
	
	div = document.getElementById('login');
	div.style.display = "none";
	div.style.visibility = "hidden";

}

function tabela(){
	let recebe;
	let xhr = new XMLHttpRequest;
	xhr.open('GET','/canal');

	xhr.onload = function(){

		if(this.status == 200){
			recebe = JSON.parse(this.responseText);
			/* for (var i = 0; i < recebe.content.length; i++) {

				 document.getElementById('canais').innerHTML += " <tr><td> " + recebe.content[i].id + "</td>"
				+ "<td>" + recebe.content[i].nome + "</td>"
				+ "<td>" + recebe.content[i].email + "</td></tr>"; 
			}*/
		}

	};
	xhr.onerro = () => alert('ERRO');
	xhr.send();
}

function update(){

	let xhr = new XMLHttpRequest;
	let  id =  document.getElementById('id').value;
	xhr.open('PUT',"/canal/" + id)
	xhr.setRequestHeader('Content-Type','application/json');

	let nome = document.getElementById('nome').value;
	let email = document.getElementById('email').value;



	let novo = {
			"nome": `${nome}`,
			"email":`${email}`
	};
	xhr.onload = function(){

		if(this.status == 200){
			let recebe = JSON.parse(this.responseText);

			document.getElementById('canalUpdate').innerHTML += " <tr><td> " + recebe.id + "</td>"
			+ "<td>" + recebe.nome + "</td>"
			+ "<td>" + recebe.email + "</td></tr>";
		}
	}

	xhr.onerro = () => alert('ERRO');
	xhr.send(JSON.stringify(novo));
	console.log('Sucesso salvo');


}
function buscarCanalPorEmail(){
	let recebe;
	let xhr = new XMLHttpRequest;
	let  email =  document.getElementById('email-cadastro').value;

	xhr.open('GET','/canal/buscarEmail/' + email);

	xhr.onload = function(){

		if(this.status == 200){
			recebe = JSON.parse(this.responseText);
			console.log(recebe);
//			for (var i = 0; i < recebe.content.length; i++) {
			let id = document.getElementById("id");
			let email = document.getElementById("email");
			let senha = document.getElementById("senha");
			nome.value = recebe.content[0].nome;
			email.value = recebe.content[0].email;
			senha.value = recebe.content[0].senha;
			id.value = recebe.content[0].id;
			localStorage.setItem("id",id.value);
			localStorage.setItem("nome",nome.value);
			localStorage.setItem("email",email.value);
			console.log(localStorage.getItem("nome"));

//			}
		}

	};
	xhr.onerro = () => alert('ERRO');
	xhr.send();
}

function delet(){
	let xhr = new XMLHttpRequest;

	let  id =  document.getElementById('id').value;
	xhr.open('DELETE',"/canal/" + id);

	xhr.onload = function(){

		if(this.status == 200){
			alert('Deletado');
			let  id =  document.getElementById('id');
			let email = document.getElementById("email");
			let senha = document.getElementById("senha");
			nome.value = "";
			email.value = "";
			senha.value = "";
			id.value = "";
		}
	}

	xhr.onerro = () => alert('ERRO');
	xhr.send();
	console.log('Deletado');
}

function logar(){ 
	let email = document.getElementById('email-login').value;
	let senha = document.getElementById('senha-login').value;

	let canal = {
		'email':`${email}`,
		'senha':`${senha}`
	};

	let xhr = new XMLHttpRequest;
	xhr.open('POST','/canal');

	xhr.setRequestHeader('Content-Type', 'application/json');



	xhr.onload = function(){

		if(this.status == 200){
			recebe = JSON.parse(this.responseText);
			console.log(recebe);
//			for (var i = 0; i < recebe.content.length; i++) {
			let id = document.getElementById("id");
			let nome = document.getElementById("nome");
			let email = document.getElementById("email");
			let senha = document.getElementById("senha");
			nome.value = recebe.nome;
			email.value = recebe.email;
			senha.value = recebe.senha;
			id.value = recebe.id;
			let div = document.getElementById('login');
			div.style.display = "none";
			div.style.visibility = "hidden";
			esconderDivCadastro();

		}

	}	
	xhr.onerro = () => alert('ERRO');
	xhr.send(JSON.stringify(canal));
}


function exibirDivCadastro(){
	let div = document.getElementById('cadastroCanal');
	div.style.display = "none";
	div.style.visibility = "hidden";
	
}

function carregarOpacaoDeUsuario(){
	
	
}


esconderDivPerfil();