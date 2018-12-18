package com.wep.iftube.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Canal implements UserDetails{
	
	@Id	
	@GeneratedValue
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String email;
	
	@Column
	@JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
	private String senha;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Historico historico;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentario = new ArrayList<Comentario>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Playlist> playlist = new ArrayList<Playlist>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
	this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Historico getHistorico() {
		return historico;
	}
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	public List<Comentario> getComentario() {
		return comentario;
	}
	public void setComentario(List<Comentario> comentario) {
		this.comentario.clear();
		this.comentario.addAll(comentario);
	}
	public List<Playlist> getPlaylist() {
		return playlist;
	}
	public void setPlaylist(List<Playlist> playlist) {
		this.playlist.clear();
		this.playlist.addAll(playlist) ;
	}
	
	public void addPlaylist( Playlist playlist ) {
		
		this.playlist.add(playlist);
		
	}
	
	
	public void removerPlaylist( Long idPlay ) {
		Playlist recebePLaylist = null;
		for (Playlist playlist : this.playlist) {
			if(playlist.getId() == idPlay) {				
				recebePLaylist = playlist;				
			}
		}
		this.playlist.remove(recebePLaylist);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}
	@Override
	public String getPassword() {
		return getSenha();
	}
	@Override
	public String getUsername() {
		return getEmail();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
	    return true;
	}
	

}
