
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

/*function get(){
	fetch("/canal").then(function(response){
			console.log("ok");
		});
	}
get();*/

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
			esconderDiv('login');
			exebirDiv('canal-main');
			esconderDivCadastro();
			buscarCanalPorEmail();

		}
	}

	xhr.onerro = () => alert('ERRO');
	xhr.send(JSON.stringify(novo));
	console.log('Sucesso salvo');


}

function esconderDivPerfil(){
	esconderDiv('opcoesPlaylist');
	esconderDiv('cadastroCanal');
	esconderDiv('cadastroPlaylist');
	esconderDiv('info-canal');
	esconderDiv('cadastroVideos');
	esconderDiv('canal-main');
	console.log('esconde');
}

function esconderDiv(recebe){
	let div = document.getElementById(recebe);
	div.style.display = "none";
	div.style.visibility = "hidden";
}

function exebirDiv(recebe){
	let div = document.getElementById(recebe);
	div.style.display = "block";
	div.style.visibility = "visible";
}

function esconderDivCadastro(){
	 esconderDiv('cadastroCanal');
	 exebirDiv('info-canal');
	}

function mostarFormularioDeCadastro(){	
	 exebirDiv('cadastroCanal');
	 esconderDiv('login');	 
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
			+ " <td> " + recebe.nome + " </td>"
			+ " <td> " + recebe.email + " </td></tr>";
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
	buscarCanalPorEmail();
	let email = document.getElementById('email-login').value;
	let senha = document.getElementById('senha-login').value;
    let nome = localStorage.getItem("nome");
	
	
	
	let canal = {
			'nome':`${nome}`,
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
			esconderDiv('login');
			exebirDiv('canal-main');
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


function visualizarOpcaoDePlaylist(){
	exebirDiv('opcoesPlaylist');
	
	visualizarTodasPaylist();
}


function addPlaylist(){

	let http = new XMLHttpRequest;
	let nome = document.getElementById('nome-playlist').value;
	let id = localStorage.getItem("id");
	http.open('POST','/canal/'+id+'/addPlaylist');
	http.setRequestHeader('Content-Type', 'application/json');

	http.onload = function(){

		if(this.status == 200){
			document.getElementById('lista-playlist-salva').innerHTML =""; 
			document.getElementById('lista-playlist-salva').innerHTML +="<div class=\"card\">"
				+"<div class=\"card-body\">" + nome  + " Salva com sucesso! </div>"
				+"</div>"
		}
	}

	let playlist = {
			'nome' : `${nome}`	
	};

	http.onerro = () => alert('ERRO');
	http.send(JSON.stringify(playlist));

}

function visualizarTodasPaylist(){
	let http = new XMLHttpRequest;

	let id = localStorage.getItem("id");
	http.open('GET','/playlist');
	document.getElementById('todas-playlist').innerHTML ="";
	http.onload = function(){

		if(this.status == 200){
			let playlists = JSON.parse(this.responseText);
			for(let i = 0; i < playlists.content.length; i++){
				console.log(playlists);

				document.getElementById('todas-playlist').innerHTML +="<div class=\"card\">"
					+"<div class=\"card-body\">id: " + playlists.content[i].id + " nome: " + playlists.content[i].nome + "</div>"
					+"</div>"
			}
			exebirDiv('cadastroPlaylist');
		}
	}
	http.onerro = () => alert('ERRO');
	http.send();
}

function visualizarPaylistSalva(){
	let http = new XMLHttpRequest;

	let id = localStorage.getItem("id");
	http.open('GET','/playlist');

	http.onload = function(){

		if(this.status == 200){
			let playlists = JSON.parse(this.responseText);
			for(let i = 0; i < playlists.content.length; i++){
				console.log(playlists);
				document.getElementById('lista-playlist').innerHTML +="<div class=\"card\">"
					+"<div class=\"card-body\">" + playlists.content[i].nome + "</div>"
					+"<div class=\"card-body\">" + playlists.content[i].video[i] + "</div>"
					+"</div>"
			}
		}
	}
	http.onerro = () => alert('ERRO');
	http.send();
}

function buscarPlaylist(){
	let http = new XMLHttpRequest;

	let id = document.getElementById("id-playlist").value;
	http.open('GET','/playlist/'+ id);

	http.onload = function(){

		if(this.status == 200){

			let playlists = JSON.parse(this.responseText);
			let nome = document.getElementById("nome-playlist-atualizado");
			nome.value = playlists.nome;
			id.value = playlists.id;
			exebirDiv('cadastroVideos');
			console.log(playlists);
		}
	}

	http.onerro = () => alert('ERRO');
	http.send();


}

function atualizarPlaylist(){
	let http = new XMLHttpRequest;
	let nome = document.getElementById("nome-playlist-atualizado").value;
	let id = document.getElementById("id-playlist").value;

	http.open('PUT','/playlist/'+id);
	http.setRequestHeader('Content-Type', 'application/json');

	http.onload = function(){

		if(this.status == 200){
			document.getElementById('lista-playlist').innerHTML =""; 
			document.getElementById('lista-playlist').innerHTML +="<div class=\"card\">"
				+"<div class=\"card-body\">" + nome  + " Atualizada com sucesso! </div>"
				+"</div>"
				visualizarTodasPaylist();
		}
	}

	let playlist = {
			'nome' : `${nome}`	
	};

	http.onerro = () => alert('ERRO');
	http.send(JSON.stringify(playlist));
}

function deletarPlaylist(){
	let http = new XMLHttpRequest;
	let id = localStorage.getItem("id");
    let idPlaylist = document.getElementById('id-playlist').value;
	http.open('DELETE','/canal/'+id+'/deletePlaylist');
	http.setRequestHeader('Content-Type', 'application/json');

	http.onload = function(){

		if(this.status == 200){
			document.getElementById('lista-playlist').innerHTML =""; 
			document.getElementById('lista-playlist').innerHTML +="<div class=\"card\">"
				+"<div class=\"card-body\"> Deletada com sucesso! </div>"
				+"</div>"
				visualizarTodasPaylist();

		}
	}

	let playlist = {
			'id' : `${idPlaylist}`	
	};

	http.onerro = () => alert('ERRO');
	http.send(JSON.stringify(playlist));
}

function addVideo(){
	let http = new XMLHttpRequest;
	let titulo = document.getElementById('titulo-video').value;
	let url = document.getElementById('url-video').value;
	let id = document.getElementById('id-playlist').value;
	console.log(id);
	http.open('POST','/playlist/'+id+'/addVideo');
	http.setRequestHeader('Content-Type', 'application/json');

	http.onload = function(){

		if(this.status == 200){
			document.getElementById('lista-video-salva').innerHTML =""; 
			document.getElementById('lista-video-salva').innerHTML +="<div class=\"card\">"
				+"<div class=\"card-body\">" + titulo  + " Salva com sucesso! </div>"
				+"</div>"
		}
	}

	let video = {
			'titulo' : `${titulo}`,
			'url' : `${url}`
	};

	http.onerro = () => alert('ERRO');
	http.send(JSON.stringify(video));
}

function carregarTodosOsVideos(){
	let http = new XMLHttpRequest; 		
	http.open('GET','/video');

	http.onload = function(){

		if(this.status == 200){
			let videos = JSON.parse(this.responseText);
			console.log(videos);
			for(let i = 0; i < videos.content.length; i++){
				document.getElementById('video-player').innerHTML +="<ul><li>"
					+"<div  class=\"video-item \">"
					+"<iframe width=\"420\" height=\"315"
					+"src=\"https://www.youtube.com/embed/"+videos.content[i].url+"\">"
					+"</iframe>"
					+"</div></li></ul>"    
			}
		}
	}
	http.onerro = () => alert('ERRO');
	http.send();

}


function carregarVideos(){
	location.href="videos.html";
}

function confirmar(){
	esconderDiv('info-canal');
	document.getElementById("canal-nome").innerHTML = "";
	document.getElementById("canal-nome").innerHTML = localStorage.getItem("nome");
}
esconderDivPerfil();
