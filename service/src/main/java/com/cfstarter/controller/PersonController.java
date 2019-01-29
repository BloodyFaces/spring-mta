package com.cfstarter.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cfstarter.domain.Person;
import com.cfstarter.service.PersonService;


@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(value="/person")
	public List<Person> getAllPerson() {
		return personService.getPersonAll();
	}
	
	@PostMapping(value="/person")
	public void createPerson(@RequestBody Person person) throws SQLException {
		personService.createPerson(person);
	}

}
