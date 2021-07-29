package com.bae.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.data.Cat;
import com.bae.service.CatService;

@RestController
public class CatController {

	private CatService service;

	public CatController(CatService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String hello() {
		return "吾輩は猫である";
	}

	@PostMapping("/createCat")
	public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
		Cat created = this.service.createCat(cat);
		return new ResponseEntity<>(created, HttpStatus.CREATED);

	}

	@GetMapping("/getAllCats")
	public ResponseEntity<List<Cat>> getAllCats() {
		return new ResponseEntity<>(this.service.getAllCats(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getCatByName/{name}")
	public ResponseEntity<List<Cat>> getByName(@PathVariable String name) {
		return new ResponseEntity<>(this.service.getByName(name), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getCat/{id}")
	public Cat getCat(@PathVariable int id) {
		return this.service.getCat(id);
	}

	@PutMapping("/replaceCat/{id}")
	public ResponseEntity<Cat> replaceCat(@PathVariable int id, @RequestBody Cat newCat) {
		Cat body = this.service.replaceCat(id, newCat);
		return new ResponseEntity<Cat>(body, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteCat/{id}")
	public ResponseEntity<String> deleteCat(@PathVariable int id) {
		String body = this.service.deleteCat(id);
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);
	}

}
