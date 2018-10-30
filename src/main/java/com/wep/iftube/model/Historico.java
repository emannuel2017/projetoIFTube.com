package com.wep.iftube.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Historico {
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Video> videos = new ArrayList<Video>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos.clear();
		this.videos.addAll(videos);
	}
	
}
