package br.com.restspringboot.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.restspringboot.Data.VO.V1.BookVO;
import br.com.restspringboot.exceptions.RequiredObjectIsNullException;
import br.com.restspringboot.models.Book;
import br.com.restspringboot.repositories.BookRepository;
import br.com.restspringboot.services.BookServices;
import br.com.restspringboot.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;

	@InjectMocks
	private BookServices service;

	@Mock
	BookRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
    List<Book> list = input.mockEntityList();

    when(repository.findAll()).thenReturn(list);

    var books = service.findAll();
    assertNotNull(books);
    assertEquals(14, books.size());

    for (int i = 0; i < books.size(); i++) {
        var book = books.get(i);

        assertNotNull(book);
        assertNotNull(book.getKey());
        assertNotNull(book.getLinks());

        String expectedLink = String.format("</api/book/v1/%d>;rel=\"self\"", i);
        assertTrue(book.toString().contains("links: [" + expectedLink + "]"));

        assertEquals("Title Test" + (i), book.getTitle());
        assertEquals("Author Test" + (i), book.getAuthor());
        assertEquals(books.get(i).getPrice(),book.getPrice());
        assertEquals(MockBook.parseDateString("2017-11-29T02:00:00.000+00:00"),book.getLaunchDate());
    }	
}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Title Test1", result.getTitle());
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(entity.getPrice(),result.getPrice());
		assertEquals(MockBook.parseDateString("2017-11-29T02:00:00.000+00:00"),result.getLaunchDate());
	}

	@Test
	void testCreate() {
		Book persisted = input.mockEntity(1);
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(any(Book.class))).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Title Test1", result.getTitle());
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(vo.getPrice(), result.getPrice());
		assertEquals(MockBook.parseDateString("2017-11-29T02:00:00.000+00:00"), result.getLaunchDate());
	}

	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1); 
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Title Test1", result.getTitle());
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(vo.getPrice(), result.getPrice());
		assertEquals(MockBook.parseDateString("2017-11-29T02:00:00.000+00:00"), result.getLaunchDate());
	}

	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});

		String expectedMessage =  "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage =  "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1); 
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}

}