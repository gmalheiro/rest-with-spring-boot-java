package br.com.restspringboot.unittests.mapper.mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.restspringboot.Data.VO.V1.BookVO;
import br.com.restspringboot.models.Book;

public class MockBook {
    

    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setTitle("Title Test" + number);
        book.setAuthor("Author Test" + number);
        book.setPrice(7.0);
        book.setId(number.longValue());
        book.setLaunchDate(parseDateString("2017-11-29T02:00:00.000+00:00"));
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setPrice(Math.random() * (10.0 - 1.0) + 1.0);
        book.setKey(number.longValue());
        book.setLaunchDate(getRandomDate());
        return book;
    }   

    public Date getRandomDate() {
        long currentTimeMillis = System.currentTimeMillis();
        
        Random rand = new Random();
        long randomTime = currentTimeMillis - (rand.nextLong() % (365 * 24 * 60 * 60 * 1000));

        return new Date(randomTime);
    }

    public static  Date parseDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace(); 
            return null;
        }
    }

}
