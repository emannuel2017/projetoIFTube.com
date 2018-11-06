package com.wep.iftube.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wep.iftube.exception.ResourceNotFoundException;
import com.wep.iftube.model.Comentario;
import com.wep.iftube.repositories.ComentarioRepository;

@RestController
public class ComentarioController {

	@Autowired
	ComentarioRepository comentarioRepository;
	
	@GetMapping("/comentario")
	public Page<Comentario> getComentario( Pageable pageable ){
		return comentarioRepository.findAll(pageable);
	}
	
	
	@PostMapping("/comentario")
	public Comentario sevaComentario(@Valid @RequestBody Comentario comentario) {
		return comentarioRepository.save(comentario);
	}
	
	@PutMapping("/comentario/{comentarioId}")
	public Comentario updateComentario(@PathVariable Long comentarioId,
			@Valid @RequestBody Comentario comentarioRequest) {
		return comentarioRepository.findById(comentarioId)
				.map( comentario -> {
				
					comentario.setTexto(comentarioRequest.getTexto());
					
					return comentarioRepository.save(comentario);
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + comentarioId));
		
	}
	
	@DeleteMapping("/comantario/{comantarioId}")
	public ResponseEntity<?> deletQuestion(@PathVariable Long comentarioId){
		return comentarioRepository.findById(comentarioId)
				.map(comentario -> {
				 
					comentarioRepository.delete(comentario);
					return ResponseEntity.ok().build(); 
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + comentarioId));
	}
	

}
