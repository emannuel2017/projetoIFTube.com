package com.wep.iftube.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import net.bytebuddy.asm.Advice.AllArguments;

@Entity
public class Canal {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String email;
	
	@Column
	private String senha;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Historico historico;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentario = new ArrayList<Comentario>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Playlist> playlist = new ArrayList<Playlist>();
	
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
	

}
