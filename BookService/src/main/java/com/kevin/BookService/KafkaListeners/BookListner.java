package com.kevin.BookService.KafkaListeners;

import com.kevin.BookService.Model.BookModel;
import com.kevin.BookService.Model.CartItemModel;
import com.kevin.BookService.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookListner {

    private final BookService bookService;

    @KafkaListener(topics = "book-stock-update", groupId = "book-service")
    public void updateAllBooks(CartItemModel cart){
        for(BookModel book : cart.getBooks()){
            book.setStockCount(book.getStockCount()- cart.getCount());
            if(book.isInStocks() && book.getStockCount()<1){
                book.setInStocks(true);
                book.setStockCount((long) 0);
            }
            bookService.updateBook(book);
        }
    }
}
