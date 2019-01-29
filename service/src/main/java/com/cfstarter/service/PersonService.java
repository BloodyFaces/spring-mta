package com.cfstarter.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfstarter.daoEntity.PersonDao;
import com.cfstarter.domain.Person;

@Service
public class PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	public List<Person> getPersonAll() {
		return personDao.getAll();
	}
	
	public Person getPerson(Long id) {
		Optional<Person> personOptional = this.personDao.getById(id);
		Person person = null;
		if (personOptional.isPresent()) {
			person = personOptional.get();
		}
		return person;
	}
	
	public void createPerson(Person translation) throws SQLException {
		this.personDao.save(translation);
	}
	
	public void updatePerson(Person translation) {
		this.personDao.update(translation);
	}
	
	public void deletePerson(Long id) {
		this.personDao.delete(id);
	}
	
}