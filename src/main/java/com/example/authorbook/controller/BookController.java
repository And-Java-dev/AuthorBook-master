package com.example.authorbook.controller;


import com.example.authorbook.model.Author;
import com.example.authorbook.model.Book;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.repository.BookRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Value("${image.upload.dir}")
    private String imageUploadDir;

    @GetMapping("/add")
    public String addBookView(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, @RequestParam("picture") MultipartFile multipartfile) {
        String filName = System.currentTimeMillis()+ " " + multipartfile.getOriginalFilename();
        File file = new File(imageUploadDir + File.separator + filName);
        try {
            multipartfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setPicUrl(filName);
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") int id) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            bookRepository.deleteById(id);
        }
        return "redirect:/";
    }

    @GetMapping("/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("picUrl") String picUrl) throws IOException {
        InputStream in = new FileInputStream(imageUploadDir + File.separator + picUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());

    }

    @GetMapping("/search")
    public String search(ModelMap modelMap, @RequestParam String keyword){
        List<Book> books = bookRepository.findByTitle(keyword);
        modelMap.addAttribute("books",books);
        return "index";
    }
}
