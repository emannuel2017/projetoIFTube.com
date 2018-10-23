package com.wep.iftube.model;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Chat {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String tema;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	
	
}
