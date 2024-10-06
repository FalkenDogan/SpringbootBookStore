package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStore.entity.MyBookList;
import com.bookStore.service.MyBookListService;

@RestController // RestController JSON döndürür
@RequestMapping("/api") // Opsiyonel: API çağrılarını ayırmak için
public class BookApiController {

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/my_books")
    public List<MyBookList> getMyBooksJson() {
        return myBookService.getAllMyBooks(); // JSON formatında kitap listesi döner
    }

    @PostMapping("/my_books")
    public ResponseEntity<MyBookList> addMyBook(@RequestBody MyBookList myBook) {
        MyBookList savedBook = myBookService.saveMyBooks(myBook);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/my_books/{id}")
    public ResponseEntity<MyBookList> updateMyBook(@PathVariable int id, @RequestBody MyBookList myBook) {
        MyBookList updatedBook = myBookService.updateMyBook(id, myBook);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @DeleteMapping("/my_books/{id}")
    public ResponseEntity<Void> deleteMyBook(@PathVariable int id) {
        myBookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
