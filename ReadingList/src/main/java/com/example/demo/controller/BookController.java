package com.example.demo.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.doa.BookRepository;
import com.example.demo.doa.ReaderRepository;
import com.example.demo.domain.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping(value = "/")
public class BookController {
	
	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	//readersBook() method used to get all books that have been registered in the database
	@GetMapping(value = "/readingList")
	public String readersBooks(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "readingList";
	}
	
	//addToReadingList method used to add a new book to the database.
	@RequestMapping(value = "/readingList", method = RequestMethod.POST)
	public String addToReadingList(@ModelAttribute("newBook") Book book, BindingResult result, HttpServletRequest request) {
		if(result.hasErrors())
			return "newBook";
		bookService.addBook(book);
		String[] supressedFields = result.getSuppressedFields();
		if(supressedFields.length > 0) 
			throw new RuntimeException("Attempting to bind disallowed fileds " + StringUtils.arrayToCommaDelimitedString(supressedFields));
		return "redirect:/readingList";
	}
	
	
	//intializeBinder() method set up the fields which allowed for binding during submission
	@InitBinder("newBook")
	public void intializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", 
				"reader",
				"isbn",
				"title",
				"author",
				"description");
	}

}
