package com.wep.iftube.controler;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.wep.iftube.seguranca.JwtTokenProvider;

@RestController
public class CanalController {

	@Autowired
	CanalRepository canalRepository;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
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
		String senha = passwordEncoder.encode(canal.getSenha());
		canal.setSenha(senha);
		return canalRepository.save(canal);

	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Map<String, String> parametro){
		 try {
	            String username = parametro.get("email");
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, parametro.get("senha")));
	            String token = jwtTokenProvider.createToken(username, this.canalRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

	            Map<Object, Object> model = new HashMap<>();
	            model.put("username", username);
	            model.put("token", token);
	            return ok(model);
	        } catch (AuthenticationException e) {
	        	e.printStackTrace();
	            throw new BadCredentialsException("Invalid username/password supplied");
	        }
	}
	
	@GetMapping("/canal/buscarName/{canalName}")
	public Page<Canal> getOneCanal(@PathVariable String canalName,
			Pageable pageable){   		
	return canalRepository.findByNome(canalName, pageable);	
	}
	
	@GetMapping("/canal/buscarEmail/{canalEmail}")
	public Page<Canal> getOneCanalEmail(@PathVariable String canalEmail,
			Pageable pageable){   		
	return canalRepository.findByEmail(canalEmail, pageable);	
	}
	@GetMapping("/canal/buscarSenha/{canalSenha}")
	public Page<Canal> getOneCanalSenha(@PathVariable String canalSenha,
			Pageable pageable){   		
	return canalRepository.findBySenha(canalSenha, pageable);	
	}
	
	
			
	@PutMapping("/canal/{canalId}")
	public Canal updateCanal(@PathVariable Long canalId,
			@Valid @RequestBody Canal canalRequest) {
		return canalRepository.findById(canalId)
				.map(canal -> {
					canal.setNome(canalRequest.getNome());
					canal.setEmail(canalRequest.getEmail());
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
