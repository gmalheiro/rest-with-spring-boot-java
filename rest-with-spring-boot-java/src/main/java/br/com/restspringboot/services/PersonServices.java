package br.com.restspringboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.restspringboot.models.Person;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	private List<Person> persons = new ArrayList<Person>();

	public List<Person> findAll() {

		logger.info("Finding all people!");
		
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			this.persons.add(person);
		}
		return this.persons;
	}

	public Person findById(String id) {
		
		logger.info("Finding one person!");
	
		int index = (int) Long.parseLong(id) - 1;
		Person person = this.persons.get(index);

		if (person == null){
			return new Person();
		}
		return person;
	}
	
	public Person create(Person person) {

		person.setId(counter.incrementAndGet());
		
		this.persons.add(person);


		logger.info("Creating one person!");
		
		return person;
	}
	
	public Person update(Person person,String id) {
		logger.info("Updating one person!");
		
		this.persons.set(Integer.parseInt(id), person);

		return person;
	}
	
	public void delete(String id) {
		
		logger.info("Deleting one person!");

		this.persons.remove(Integer.parseInt(id) -1);

	}
	
	private Person mockPerson(int i) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some address in Brasil " + i);
		person.setGender("Male");
		return person;
	}
    
}
