package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookStore.entity.MyBookList;
import com.bookStore.service.MyBookListService;

@Controller
public class MyBookListController {

    @Autowired
    private MyBookListService service;

    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/my_books";
    }

    // Kitap güncelleme işlemi için yeni bir endpoint
    @PostMapping("/updateMyBook/{id}")
    public String updateMyBook(@PathVariable("id") int id, @ModelAttribute MyBookList updatedBook) {
        MyBookList existingBook = service.updateMyBook(id, updatedBook);
        if (existingBook != null) {
            return "redirect:/my_books"; // Güncelleme başarılıysa yönlendirme
        } else {
            return "error"; // Hata sayfasına yönlendirme (gerekirse)
        }
    }

}

