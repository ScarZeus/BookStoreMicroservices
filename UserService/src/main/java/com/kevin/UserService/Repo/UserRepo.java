package com.kevin.UserService.Repo;

import com.kevin.UserService.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String emailAddress);
}
