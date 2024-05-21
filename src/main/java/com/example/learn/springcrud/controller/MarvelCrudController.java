package com.example.learn.springcrud.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.learn.springcrud.data.MarvelCharacters;
import com.example.learn.springcrud.service.MarvelCrudService;

@RestController
public class MarvelCrudController {

	@Autowired
	private MarvelCrudService service;

	// write heros in the database, response of success or failure message
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

	// get the hero from database, response of the object of type MarvelCharacter, if success else error response "ID not found" 
	@GetMapping(path = "/heroDetails/{id}")
	public ResponseEntity<?> getHeroDetails(@PathVariable int id) {
		Optional<MarvelCharacters> characterData = service.findById((long) id);
		
		if(characterData.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterData);
	}
	
	// get details of all heros
	@GetMapping(path = "/heroDetails/all")
	public ResponseEntity<?> getAllHerosDetails(){
		
		List<MarvelCharacters> allCharacters = service.findAll();
		
		if(allCharacters.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("No Heros added yet");
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allCharacters);
	}
	
	// update details of hero
	@PutMapping(path = "/updateHero/{id}")
	public ResponseEntity<?> updateHeroDetails(@RequestBody MarvelCharacters updatedCharacter, @PathVariable int id){
		
		Optional<MarvelCharacters> character = service.findById((long) id);	// check for the record
		
		if(character.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("ID not found");	// if record not found then return
		}
		
		// set the id to the updated value object and save to db
		updatedCharacter.setId(character.get().getId());
		MarvelCharacters savedCharacter = service.save(updatedCharacter);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedCharacter);
	}

	@DeleteMapping(path = "/deleteHero/{id}")
	public String deleteHero(@PathVariable int id) {
		
		Optional<MarvelCharacters> character = service.findById((long) id);
		
		if(character.isEmpty()) {
			return "ID Not Found";
		}
		
		service.delete(character.get());
		
		return "Character Deleted";
	}
}
