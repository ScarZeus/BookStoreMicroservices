package com.kevin.BookService.KafkaListeners;

import com.kevin.BookService.Model.BookModel;
import com.kevin.BookService.Model.CartItemModel;
import com.kevin.BookService.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookListner {

    private final BookService bookService;

    @KafkaListener(topics = "book-stock-update", groupId = "book-service")
    public void updateAllBooks(List<CartItemModel> cart){
        for(CartItemModel item : cart){
           BookModel book = item.getBook();
           book.setStockCount(book.getStockCount()-item.getCount());
           if(book.getStockCount()<1 && book.isInStocks()){
               book.setInStocks(false);
           }
            bookService.updateBook(book);
        }
    }
}
