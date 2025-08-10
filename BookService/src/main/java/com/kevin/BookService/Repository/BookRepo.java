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


    @Query("SELECT book FROM BookModel book WHERE book.title ILIKE %:bookName%")
    Optional<List<BookModel>> findAllByTitle(@Param("bookName") String bookName);
}
