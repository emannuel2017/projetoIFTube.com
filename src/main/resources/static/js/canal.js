let http = new XMLHttpRequest;
alert("oi")
function addPlaylist()(){
	let id = localStorage("id");
	http.open('POST','/canal/'+id+'/addPlaylist');
	http.setRequestHeader('Content-Type', 'application/json');

	
	let nome = document.getElementById('nome-playlist').value;
	
	let playlist = {
			'nome' = `${nome}`	
	};
	
	http.onerro = () => alert('ERRO');
    http.send(JSON.stringify(playlist));
	
}