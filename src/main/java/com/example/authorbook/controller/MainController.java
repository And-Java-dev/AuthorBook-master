package com.example.authorbook.controller;

import com.example.authorbook.model.Author;
import com.example.authorbook.model.Book;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
     private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/")
    public String main(ModelMap map){
        List<Book> books = bookRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        map.addAttribute("books",books);
        map.addAttribute("authors",authors);
        return "index";
    }


}
