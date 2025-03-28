package com.api.bookNest.repository;

import com.api.bookNest.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookModel,Long> {

    boolean existsByIbsnId(String ibsnId);

    Optional<BookModel> findByIbsnId(String id);

    Optional<List<BookModel>> findByBookNameContainingIgnoreCase(String bookName);
}
