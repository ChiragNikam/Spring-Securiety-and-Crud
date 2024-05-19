package com.example.learn.springcrud.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MarvelCharacters {
	
	public MarvelCharacters() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarvelCharacters(int id,String name, String humanName) {
		super();
		this.id = id;
		this.name = name;
		this.humanName = humanName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String humanName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

}
