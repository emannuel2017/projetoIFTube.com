package com.wep.iftube.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Video {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String titulo;
	
	@Column
	private int duracao;
	
	@Column
	private String descricao;
	
	@Column
	private Long visualizacoes;
	
	@Column
	private String url;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Chat> chat = new ArrayList<Chat>();
	
	@Column
	private LocalDateTime dataDePostagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getVisualizacoes() {
		return visualizacoes;
	}

	public void setVisualizacoes(Long visualizacoes) {
		this.visualizacoes = visualizacoes;
	}

	public List<Chat> getChat() {
		return chat;
	}

	public void setChat(List<Chat> chat) {
		this.chat.clear();
		this.chat.addAll(chat);
	}

	public LocalDateTime getDataDePostagem() {
		return dataDePostagem;
	}

	public void setDataDePostagem(LocalDateTime dataDePostagem) {
		this.dataDePostagem = dataDePostagem;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
