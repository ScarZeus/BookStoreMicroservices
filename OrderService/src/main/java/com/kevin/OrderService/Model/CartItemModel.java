package com.kevin.OrderService.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = true)
    private BillModel bill;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel book;

    private Long count;
}
