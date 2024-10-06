package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;

@Service
public class MyBookListService {

    @Autowired
    private MyBookRepository mybook;

    // Artık kaydedilen kitabı döndürür
    public MyBookList saveMyBooks(MyBookList book) {
        return mybook.save(book); // Kaydedilen nesneyi döndür
    }

    public List<MyBookList> getAllMyBooks() {
        return mybook.findAll();
    }

    public void deleteById(int id) {
        mybook.deleteById(id);
    }

    public MyBookList updateMyBook(int id, MyBookList updatedBook) {
        MyBookList existingBook = mybook.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setName(updatedBook.getName());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());
            return mybook.save(existingBook); // Güncellenmiş nesneyi döndür
        }
        return null; // Eğer kitap bulunamazsa null döner
    }
}
