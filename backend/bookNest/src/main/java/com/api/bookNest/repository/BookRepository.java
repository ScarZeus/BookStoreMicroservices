package com.api.bookNest.repository;

import com.api.bookNest.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel,Long> {

}
