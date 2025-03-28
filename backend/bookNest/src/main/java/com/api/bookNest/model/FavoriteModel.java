package com.api.bookNest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "favorite_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
