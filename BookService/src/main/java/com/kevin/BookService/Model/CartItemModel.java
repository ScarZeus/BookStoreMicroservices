package com.kevin.BookService.Model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemModel {

    private Long cartId;

    private BillModel bill;

    private BookModel book;

    private Long count;
}
