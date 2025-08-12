package com.kevin.BookService.Model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemModel {
    private List<BookModel> books;
    private Long count;
}
