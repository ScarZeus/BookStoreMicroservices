package com.kevin.BookService.Repository;

import com.kevin.BookService.Model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepo extends JpaRepository<BookModel,Long> {


    @Query("""
    SELECT book 
    FROM BookModel book 
    WHERE LOWER(book.title) LIKE LOWER(CONCAT('%', :bookName, '%')) 
       OR (:isbnNO IS NULL OR :isbnNO = '' OR book.isbn LIKE CONCAT('%', :isbnNO, '%'))
""")
    List<BookModel> findAllByTitleOrIsbn(@Param("bookName") String bookName,
                                         @Param("isbnNO") String isbnNO);


    @Query("SELECT CASE WHEN COUNT(book) > 0 THEN TRUE ELSE FALSE END FROM BookModel book WHERE book.isbn = :isbn")
    boolean existsByIsbn(@Param("isbn") String isbn);

}
