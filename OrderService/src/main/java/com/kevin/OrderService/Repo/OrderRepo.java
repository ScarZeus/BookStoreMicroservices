package com.kevin.OrderService.Repo;

import com.kevin.OrderService.Model.BillModel;
import com.kevin.OrderService.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<BillModel,Long> {
    List<BillModel> findAllByUser(UserModel user);
}
