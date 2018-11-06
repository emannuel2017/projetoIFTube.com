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
import com.wep.iftube.model.Historico;
import com.wep.iftube.repositories.HistoriaRepository;

@RestController
public class HistoricoController {

	@Autowired 
	HistoriaRepository historiaRepository;
	
	@GetMapping("/historico")
	public Page<Historico> getHistorico(Pageable pageable){
		return historiaRepository.findAll(pageable);
	}
	
	@PostMapping("/historico")
	public Historico save(@Valid @RequestBody Historico historico) {
		return historiaRepository.save(historico);
	}
	
	@PutMapping("/historico/{historicoId}")
	public Historico updateHistorico(@PathVariable Long historicoId, 
			@Valid @RequestBody Historico historicoRequest) {
		return historiaRepository.findById(historicoId)
				.map(historico -> {
					
					historico.setVideos(historicoRequest.getVideos());
				 
					return historiaRepository.save(historico);
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + historicoId));
	}
	
	@DeleteMapping("/historico/{historicoId}")
	public ResponseEntity<?> delete(@PathVariable Long historicoId){
		return historiaRepository.findById(historicoId)
				.map(historico -> {
					historiaRepository.delete(historico);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("página não encontrada" + historicoId));
	}
	
}
