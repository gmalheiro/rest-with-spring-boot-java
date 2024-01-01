package br.com.restspringboot.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restspringboot.models.Person;
import br.com.restspringboot.services.PersonServices;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices service;

	@RequestMapping(method=RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
		return service.findAll();
	}
	


}