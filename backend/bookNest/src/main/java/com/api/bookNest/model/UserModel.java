package com.api.bookNest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name ="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String email;
}
