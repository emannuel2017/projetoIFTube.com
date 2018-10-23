package com.wep.iftube.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Playlist {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nome;
	
	@OneToMany
	private List<Video> video;
	
	@Column
	private int quantidadeDeVideo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Video> getVideo() {
		return video;
	}
	public void setVideo(List<Video> video) {
		this.video = video;
	}
	public int getQuantidadeDeVideo() {
		return quantidadeDeVideo;
	}
	public void setQuantidadeDeVideo(int quantidadeDeVideo) {
		this.quantidadeDeVideo = quantidadeDeVideo;
	}
}
