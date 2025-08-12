package com.kevin.BookService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String publicId; // Cloudinary ID
    private String format;
    private long size;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;
}
