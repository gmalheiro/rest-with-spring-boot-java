package br.com.restspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restspringboot.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
