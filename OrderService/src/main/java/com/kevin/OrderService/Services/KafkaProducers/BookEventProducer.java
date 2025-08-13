package com.kevin.OrderService.Services.KafkaProducers;

import com.kevin.OrderService.Model.CartItemModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookEventProducer{

        private final KafkaTemplate<String, List<CartItemModel>> kafkaTemplate;

        public void sendStockUpdate(List<CartItemModel> cartItems) {
            kafkaTemplate.send("book-stock-update", cartItems);
        }
}

