package com.example.authorbook.repository;

import com.example.authorbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
   List<Book>findByTitle(String title);
}
