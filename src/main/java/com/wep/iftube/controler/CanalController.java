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
import com.wep.iftube.model.Canal;
import com.wep.iftube.model.Playlist;
import com.wep.iftube.repositories.CanalRepository;
import com.wep.iftube.repositories.PlaylistRepository;

@RestController
public class CanalController {

	@Autowired
	
	CanalRepository canalRepository;
	
	@GetMapping("/canal")
	public Page<Canal> getCanal(Pageable pageable){
		return canalRepository.findAll(pageable);
	}
	
	@GetMapping("/canal/{canalId}")
	public Canal getOneCanal(@PathVariable Long canalId){   		
	return canalRepository.findById(canalId).orElseThrow(()-> new  ResourceNotFoundException("página não encontrada" + canalId));
	
	}
	
	@PostMapping("/canal")
	private Canal createCanal(@Valid @RequestBody Canal canal) {
		return canalRepository.save(canal);

	}
	
	
		
	@PutMapping("/canal/{canalId}")
	public Canal updateCanal(@PathVariable Long canalId,
			@Valid @RequestBody Canal canalRequest) {
		return canalRepository.findById(canalId)
				.map(canal -> {
					canal.setNome(canalRequest.getNome());
					canal.setEmail(canalRequest.getEmail());
					canal.setSenha(canalRequest.getSenha());
					canal.setHistorico(canalRequest.getHistorico());
					canal.setComentario(canalRequest.getComentario());
					canal.setPlaylist(canalRequest.getPlaylist());
					
					return canalRepository.save(canal);
				}).orElseThrow(() -> new ResourceNotFoundException("Página não encontrada"
					+ canalId));
		
	}
	
	@DeleteMapping("/canal/{canalId}")
	public ResponseEntity<?> deletQuestion(@PathVariable Long canalId){
		return canalRepository.findById(canalId)
				.map(canal -> {
					canalRepository.delete(canal);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Página não encontrada" + canalId));
	}
	
	@PostMapping("/canal/{canalId}/addPlaylist")
	public Canal addPlaylist (@PathVariable Long canalId, 
			@Valid @RequestBody Playlist playlist) {
		
		return canalRepository.findById(canalId)
				.map(canal -> {
					
					canal.addPlaylist(playlist);
					return canalRepository.save(canal);
							
				}).orElseThrow(() -> new ResourceNotFoundException("Página não encontrada"
						+ canalId));
		
	}

	@DeleteMapping("/canal/{canalId}/deletePlaylist")
	public Canal deletePlaylist (@PathVariable Long canalId,
			@Valid @RequestBody Playlist playlist) {
		
		return canalRepository.findById(canalId)
				.map(canal -> {
					
					canal.removerPlaylist(playlist.getId());
					return canalRepository.save(canal);
							
				}).orElseThrow(() -> new ResourceNotFoundException("Página não encontrada"
						+ canalId));
		
	}

}
