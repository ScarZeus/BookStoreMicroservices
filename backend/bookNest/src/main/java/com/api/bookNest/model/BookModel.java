package com.api.bookNest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="Books")
@ToString
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String ibsnId;

    @Column(nullable = false)
    private String filePublicId;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String bookResourceUrl;

    @PrePersist
    public void setUpTheDate(){
        this.dateTime= LocalDateTime.now();
    }
}
