package com.api.bookNest.services.BookService;

import com.api.bookNest.model.BookModel;
import com.api.bookNest.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepo;

    private BookModel addBook(BookModel book){
        if(bookRepo.existsByIbsnId(book.getIbsnId())){
            throw new RuntimeException("Book Already exists");
        }
        else{
            return bookRepo.save(book);
        }
    }

    private void delete(BookModel book){
        if(bookRepo.existsByIbsnId(book.getIbsnId())){
            bookRepo.delete(book);
        }

        else{
            throw new RuntimeException("Book does not exists");
        }
    }

    private void updateBook(BookModel book){
        if(bookRepo.existsByIbsnId(book.getIbsnId())){
            bookRepo.save(book);
        }
        else {
            throw new RuntimeException("Book does not exists");
        }
    }

    private Optional<BookModel> getBookByIbsn(String id){
        return Optional.ofNullable(bookRepo.findByIbsnId(id)
                .orElseThrow(()->new RuntimeException("Book does not exist")));
    }

    private Optional<List<BookModel>> getBookByName(String bookName){
        Optional<List<BookModel>> books = Optional.ofNullable(bookRepo.findByBookNameContainingIgnoreCase(bookName)
                .orElseThrow(()->new RuntimeException("No Books were found matching")));
        return books;
    }
}
