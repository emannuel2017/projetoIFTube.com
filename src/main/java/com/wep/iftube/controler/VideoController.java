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
import com.wep.iftube.repositories.VideoRepository;

@RestController
public class VideoController {

	@Autowired
	VideoRepository videoRepository;
	
	@GetMapping("/video")
	public Page<Video> getVideo(Pageable pageable){
		return videoRepository.findAll(pageable);
	}
	
	@PostMapping("/video")
	public Video createVideo(@Valid @RequestBody Video video) {
		return videoRepository.save(video);
	}
	
	@PutMapping("/video/{videoId}")
	public Video updatePLaylist(@PathVariable Long videoId,
			@Valid @RequestBody Video videoRequest) {
		return videoRepository.findById(videoId)
				.map(video -> {
					video.setTitulo(videoRequest.getTitulo());
					video.setDataDePostagem(videoRequest.getDataDePostagem());
					video.setChat(videoRequest.getChat());
					video.setDescricao(videoRequest.getDescricao());
					video.setUrl(videoRequest.getUrl());
					video.setDuracao(videoRequest.getDuracao());
					return videoRepository.save(video);
										
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + videoId));

	}
	
	@DeleteMapping("/video/{videoId}")
	public ResponseEntity<?> delete(@PathVariable Long videoId){
		
		return videoRepository.findById(videoId)
				.map(video -> {
					videoRepository.delete(video);
					
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + videoId));
		
	}
	
	/*@PostMapping("/playlist/{videoId}/addVideo")
	public Playlist addVideo (@PathVariable Long playId, @Valid @RequestBody Video video) {
		
		return videoRepository.findById(videoId).map(playlist -> {
			playlist.addVideo(video);
			return playlistRepository.save(playlist);
		}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + videoId));
	}
	
	@DeleteMapping("/playlist/{videoId}/deleteVideo")
	public Playlist deleteVideo(@PathVariable Long playId, @Valid @RequestBody Video video){
		
		return videoRepository.findById(videoId)
				.map(playlist -> {
					playlist.removerVideo(video);
					
					return videoRepository.save(playlist);
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + videoId));
		
	}*/
	
	
}
