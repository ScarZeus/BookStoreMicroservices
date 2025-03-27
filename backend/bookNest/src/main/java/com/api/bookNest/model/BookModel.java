package com.api.bookNest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="Books")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;
    private String bookName;
    private String filePublicId;
    private LocalDateTime dateTime;
    private String bookResourceUrl;

    @PrePersist
    public void setUpTheDate(){
        this.dateTime= LocalDateTime.now();
    }
}
