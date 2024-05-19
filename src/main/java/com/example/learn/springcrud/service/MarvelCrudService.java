package com.example.learn.springcrud.service;

import org.springframework.stereotype.Service;

import com.example.learn.springcrud.data.MarvelCharacters;

import org.springframework.data.jpa.repository.JpaRepository;

@Service
public interface MarvelCrudService extends JpaRepository<MarvelCharacters, Long> {
	
	

}
