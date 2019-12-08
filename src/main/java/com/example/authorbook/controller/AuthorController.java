package com.example.authorbook.controller;

import com.example.authorbook.model.Author;
import com.example.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/add")
    public String addAuthorView() {
        return "addAuthor";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") int id ){
        authorRepository.deleteById(id);
        return "redirect:/";
    }
}
