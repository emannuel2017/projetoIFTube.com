


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
 let recebe; 
 xhr.open('POST','/canal');
 
 xhr.onload = function(){
	 
	 if(this.status == 200){
		 console.log('Sucesso');
		 recebe = JSON.parse(this.responseText);
		 console.log(recebe);
for (var i = 0; i < recebe.content.length; i++) {
			 
			 document.getElementById('canais').innerHTML += " <tr><td> " + recebe.content[i].id + "</td>"
			+ "<td>" + recebe.content[i].nome + "</td>"
			+ "<td>" + recebe.content[i].email + "</td></tr>"; 
		}
	 }
	 
 };
 
 
 
 
 xhr.setRequestHeader('Content-Type', 'application/json');
 
 /*let novo = {"id": 3,
         "nome": "Pedrosonaro",
         "email": "Pedrosonaro@vaila",
         "senha":                "12354",
         "historico": null,
         "comentario": [
             {
                 "id": 4,
                 "texto": "text"
             }
         ],
         "playlist": []
       };
 */
 let nome = document.getElementById('nome').value;
 
 let novo = {"nome": nome }
 
 xhr.send(JSON.stringify(novo));
 

