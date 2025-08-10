package com.kevin.BookService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class BookModel {
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
    @ElementCollection
    @CollectionTable(name = "book_gallery", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "image_url")
    private List<String> galleryUrls;
    private Instant publishedAt;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    @PreUpdate
    public void preUpdate() { updatedAt = Instant.now(); }


}
