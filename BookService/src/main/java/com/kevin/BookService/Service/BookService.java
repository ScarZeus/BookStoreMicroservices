package com.kevin.BookService.Service;


import com.kevin.BookService.Model.BookModel;
import com.kevin.BookService.Repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookDao;


    public List<BookModel> getAllBooks(){
        try{
            List<BookModel> books = bookDao.findAll();
            if (books.isEmpty() || books == null){
                throw new RuntimeException("No Books Found");
            }
            return books;
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    public List<BookModel> getAllBooksByName(String bookName){
        try {
            List<BookModel> books = bookDao.findAllByTitle(bookName);

        }
    }
}
