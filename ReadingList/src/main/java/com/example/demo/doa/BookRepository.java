package com.example.demo.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Book;
import com.example.demo.domain.Reader;

public interface BookRepository extends JpaRepository<Book, Long>  {
	
	List<Book> findByReader(String reader);
	void addBook(Book book);
	Book getByReader(String reader);

}
