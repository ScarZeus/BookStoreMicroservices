package com.api.bookNest.repository;

import com.api.bookNest.model.FavoriteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteModel,Long> {
}
