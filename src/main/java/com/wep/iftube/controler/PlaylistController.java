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
import com.wep.iftube.model.Playlist;
import com.wep.iftube.model.Video;
import com.wep.iftube.repositories.PlaylistRepository;

@RestController
public class PlaylistController {

	@Autowired
	PlaylistRepository playlistRepository;
	
	@GetMapping("/playlist")
	public Page<Playlist> getPLaylist(Pageable pageable){
		return playlistRepository.findAll(pageable);
	}
	
	@GetMapping("/playlist/{playlistId}")
	public Playlist getPLaylist(@PathVariable Long playlistId){
		return playlistRepository.findById(playlistId)
				.orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + playlistId));
	}
	
	@PostMapping("/playlist")
	public Playlist savePLaylist(@Valid @RequestBody Playlist playlist) {
		return playlistRepository.save(playlist);
	}
	
	@PutMapping("/playlist/{playId}")
	public Playlist updatePLaylist(@PathVariable Long playId,
			@Valid @RequestBody Playlist playRequest) {
		return playlistRepository.findById(playId)
				.map(play -> {
					play.setNome(playRequest.getNome());
					play.setQuantidadeDeVideo(playRequest.getQuantidadeDeVideo());
					play.setVideo(playRequest.getVideo());
					
					return playlistRepository.save(play);
										
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + playId));

	}
	
	@DeleteMapping("/playlist/{playId}")
	public ResponseEntity<?> delete(@PathVariable Long playId){
		
		return playlistRepository.findById(playId)
				.map(playlist -> {
					playlistRepository.delete(playlist);
					
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + playId));
		
	}
	
	@PostMapping("/playlist/{playId}/addVideo")
	public Playlist addVideo (@PathVariable Long playId, @Valid @RequestBody Video video) {
		
		return playlistRepository.findById(playId).map(playlist -> {
			playlist.addVideo(video);
			return playlistRepository.save(playlist);
		}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + playId));
	}
	
	@DeleteMapping("/playlist/{playId}/deleteVideo")
	public Playlist deleteVideo(@PathVariable Long playId, @Valid @RequestBody Video video){
		
		return playlistRepository.findById(playId)
				.map(playlist -> {
					playlist.removerVideo(video);
					
					return playlistRepository.save(playlist);
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + playId));
		
	}
	
}
