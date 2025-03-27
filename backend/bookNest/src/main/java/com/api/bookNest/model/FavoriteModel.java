package com.api.bookNest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "favorite_books")
public class FavoriteModel {
    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private BookModel book;
}
