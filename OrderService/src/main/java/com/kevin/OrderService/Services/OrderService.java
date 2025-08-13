package com.kevin.OrderService.Services;

import com.kevin.OrderService.Model.BillModel;
import com.kevin.OrderService.Model.CartItemModel;
import com.kevin.OrderService.Model.UserModel;
import com.kevin.OrderService.Repo.OrderRepo;
import com.kevin.OrderService.Services.KafkaProducers.BookEventProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepo billRepo;
    private final BookEventProducer producer;

    public BillModel createBill(UserModel user, List<CartItemModel> items) {
        BillModel bill = new BillModel();
        bill.setUser(user);
        bill.setTotalAmount(items.stream()
                .mapToDouble(i -> i.getBook().getPrice() * i.getCount())
                .sum());

        for (CartItemModel item : items) {
            item.setBill(bill);
        }
        bill.setCartItems(items);
        producer.sendStockUpdate(items);
        return billRepo.save(bill);
    }

    public List<BillModel> getBillByUser(UserModel user){
        try {
            List<BillModel> bills = billRepo.findAllByUser(user);
            if(bills==null || bills.isEmpty()){
                return new ArrayList<>();
            }
            return bills;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
