package br.com.restspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restspringboot.models.Person;

public interface PersonRepository extends JpaRepository<Person,Long>{}
