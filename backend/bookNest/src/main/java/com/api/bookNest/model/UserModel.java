package com.api.bookNest.model;

import com.api.bookNest.model.EnumModels.Role;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;
}
