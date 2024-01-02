package br.com.restspringboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restspringboot.models.Person;
import br.com.restspringboot.repositories.PersonRepository;
import br.com.restspringboot.exceptions.ResourceNotFoundException;

@Service
public class PersonServices {
	
	@Autowired PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll() {

		logger.info("Finding all people!");
		
		var people = repository.findAll();

		return people;
	}

	public Person findById(Long id) {
		
		logger.info("Finding one person!");

		var person =  repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		repository.save(person);

		return person;
	}
	
	public Person update(Person person,String id) {
		logger.info("Updating one person!");
		
		var personInDb = repository.findById(person.getId())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		personInDb.setFirstName(person.getFirstName());
		personInDb.setLastName(person.getLastName());
		personInDb.setAddress(person.getAddress());
		personInDb.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		repository.delete(entity);
	} 
}
