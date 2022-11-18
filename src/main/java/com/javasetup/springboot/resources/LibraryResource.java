package com.javasetup.springboot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasetup.springboot.entity.db1.Book;
import com.javasetup.springboot.entity.db2.User;
import com.javasetup.springboot.repository.db1.BookRepository;
import com.javasetup.springboot.repository.db2.UserRepository;

@RestController
public class LibraryResource {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BookRepository bookRepo;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}
}
