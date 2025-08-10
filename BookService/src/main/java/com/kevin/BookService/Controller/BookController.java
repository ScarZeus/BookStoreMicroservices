package com.kevin.BookService.Controller;

import com.kevin.BookService.Model.BookModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {


    @GetMapping("/getAllBooks")
    public ResponseEntity<BookModel> getAllBooks(){
        try{
            return ResponseEntity.of()
        }
    }
}
