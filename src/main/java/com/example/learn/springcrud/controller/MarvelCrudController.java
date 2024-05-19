package com.example.learn.springcrud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learn.springcrud.data.MarvelCharacters;
import com.example.learn.springcrud.service.MarvelCrudService;

@RestController
public class MarvelCrudController {

	@Autowired
	private MarvelCrudService service;

	@PostMapping(path = "/createHero")
	public ResponseEntity<?> createSuperHero(@RequestBody MarvelCharacters character) {

		if (character.getName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter Name of hero");
		}
		if (character.getHumanName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter Human Name of hero");
		}

		try {
			service.save(character);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hero Created Successfully!");
	}

	@GetMapping(path = "/heroDetails/{id}")
	public ResponseEntity<?> getHeroDetails(@PathVariable int id) {
		Optional<MarvelCharacters> characterData = service.findById((long) id);
		
		if(characterData.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterData);
	}

}
