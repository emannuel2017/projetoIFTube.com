


/*
 let xhr = new XMLHttpRequest();

 xhr.open('GET','/canal/1');

 xhr.onload = function(){
	 //alert(`Status: ${this.status} -- ${this.responseText}`)
  if(this.status != 200)console.log("Erro!"); 
  else{
	  console.log(this.responseText);
	  
	  let a = JSON.parse(this.responseText);
	  console.log(a);
  }
 
 };
 xhr.onerro = () => alert('ERRO');
 xhr.send();*/
  let xhr = new XMLHttpRequest();
  

function tabela(){
 let recebe; 
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

function save(){
 
	 xhr.open('POST','/canal');

	 xhr.setRequestHeader('Content-Type', 'application/json');
	 
	 
	 
	 let nome = document.getElementById('nome').value;
	 let email = document.getElementById('email').value;
	
	 
	 let novo = {
	         "nome": `${nome}`,
	         "email":`${email}`
	         
	       };
	 xhr.onerro = () => alert('ERRO');
	 xhr.send(JSON.stringify(novo));
	 console.log('Sucesso salvo');
	
	
}


function update(){	
	let  id =  document.getElementById('id').value;
	xhr.open('PUT',"/canal/" + id)
	xhr.setRequestHeader('Content-Type','application/json');
	
	 let nome = document.getElementById('nome').value;
	 let email = document.getElementById('email').value;
	 
	
	 
	 let novo = {
			 "nome": `${nome}`,
	         "email":`${email}`
	       };
	 
	 xhr.onerro = () => alert('ERRO');
	 xhr.send(JSON.stringify(novo));
	 console.log('Sucesso salvo');
	
}

function delet(){
	
	let  id =  document.getElementById('id').value;
	xhr.open('DELETE',"/canal/" + id)
	
	
	
	 xhr.onerro = () => alert('ERRO');
	 xhr.send();
	 console.log('Sucesso salvo');
}