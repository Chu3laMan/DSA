package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.doa.BookRepository;
import com.example.demo.domain.Book;

@Controller
@RequestMapping(value = "/")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping(value = "/{reader}", method = RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		model.addAttribute("books", bookRepository.findByReader(reader));
		return "books";
	}
	
	
	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable ("reader") String reader, Book book) {
		book.setReader(reader);
		bookRepository.save(book);
		return "redirect:/{reader}";
	}

}
