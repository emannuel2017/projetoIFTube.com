

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

//  if (screen.width > 1024) {
// if ( $('.portipolio-sec').length > 0 ) {

//    AOS.init({
//         easing: 'ease-in-out-sine'
//       });
// }
//  }

if ( $('.videos-sec').length > 0 ) {
	$.wmBox();
}

function save(){
    let xhr = new XMLHttpRequest;

	xhr.open('POST','/canal');

	xhr.setRequestHeader('Content-Type', 'application/json');
	
	
	
	let nome = document.getElementById('nome').value;
	let email = document.getElementById('email').value;
    let senha = document.getElementById('senha').value;
	
	let novo = {
			"nome": `${nome}`,
			"email":`${email}`,
			"senha":`${senha}`
		  };
	
 xhr.onload = function(){
		 
		 if(this.status == 200){
			 tabela();		 
		 }
		 }
		  
	xhr.onerro = () => alert('ERRO');
	xhr.send(JSON.stringify(novo));
	console.log('Sucesso salvo');
	
   
}
function tabela(){
	 let recebe;
	 let xhr = new XMLHttpRequest;
	 xhr.open('GET','/canal');
	 
	 xhr.onload = function(){
		 
		 if(this.status == 200){
			 recebe = JSON.parse(this.responseText);
			 for (var i = 0; i < recebe.content.length; i++) {
				 
				 document.getElementById('canais').innerHTML += " <tr><td> " + recebe.content[i].id + "</td>"
				+ "<td>" + recebe.content[i].nome + "</td>"
				+ "<td>" + recebe.content[i].email + "</td></tr>"; 
			}
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
function buscarCanal(){
	 let recebe;
	 let xhr = new XMLHttpRequest;
     let  id =  document.getElementById('id').value;

	 xhr.open('GET','/canal/' + id);
	 
	 xhr.onload = function(){
		 
		 if(this.status == 200){
			 recebe = JSON.parse(this.responseText);
			 console.log(recebe);
//			 for (var i = 0; i < recebe.content.length; i++) {
//				 let nome = document.getElementById("nome");
				let email = document.getElementById("email");
				let senha = document.getElementById("senha");
				nome.value = recebe.nome;
				email.value = recebe.email;
				senha.value = recebe.senha;
				
				
		    
				 
//			}
		 }
		 
	 };
	 xhr.onerro = () => alert('ERRO');
	 xhr.send();
	}
