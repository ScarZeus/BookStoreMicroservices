package com.kevin.OrderService.Repo;

import com.kevin.OrderService.Model.CartItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartItemModel,Long> {
}
