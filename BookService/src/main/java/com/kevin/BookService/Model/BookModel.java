package com.kevin.BookService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

    @Entity
    @Getter
    @Setter
    @ToString
    public class BookModel implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String author;

        @Column(columnDefinition = "text")
        private String description;

        private String isbn;

        private Double price;

        private String coverImageUrl;

        private boolean inStocks;

        private Long stockCount;

        @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
        private List<ImageModel> images;

        private Instant publishedAt;

        private Instant createdAt = Instant.now();

        private Instant updatedAt = Instant.now();

        @PreUpdate
        public void preUpdate() { updatedAt = Instant.now(); }


    }
