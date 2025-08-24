package com.kevin.BookService.Controller;

import com.kevin.BookService.Model.BookModel;
import com.kevin.BookService.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookModel>> getAllBooks(){
        try{
            return new ResponseEntity(bookService.getAllBooks(), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }


    @PostMapping(value = "/addBook",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BookModel> addBook(@RequestPart("book") BookModel book,
                                             @RequestPart("bookImages")MultipartFile[] images
                               ){
      try{
          return new ResponseEntity<>(bookService.saveBook(book,images),HttpStatusCode.valueOf(200));
      }
      catch (Exception e){
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletBook(BookModel book){
        try{
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateBook(BookModel book){
        try{
            bookService.updateBook(book);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }


    @GetMapping("/getBooks")
    public ResponseEntity<List<BookModel>> getBooks(@RequestParam("name") String title,@RequestParam("ibsno") String ibsn){
        try{
            return ResponseEntity.ok(bookService.getAllBooksByName(title,ibsn));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

}
