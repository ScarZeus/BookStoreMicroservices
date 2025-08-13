package com.kevin.BookService.Model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class BillModel {
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    @OneToMany
    private List<CartItemModel> cartItems;
}
