package com.kevin.OrderService.Controller;

import com.kevin.OrderService.Model.BillModel;
import com.kevin.OrderService.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class BillController {

    private final OrderService billingService;

    @PostMapping
    public ResponseEntity<BillModel> createBill(@RequestBody BillModel request) {
        BillModel bill = billingService.createBill(request.getUser(), request.getCartItems());
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<BillModel>> getBillsByUser(@PathVariable String email) {
        List<BillModel> bills = billingService.getBillByUser(email);
        return ResponseEntity.ok(bills);
    }

    // 3️⃣ Cancel a bill
    @DeleteMapping("/{billId}")
    public ResponseEntity<String> cancelBill(@PathVariable Long billId) {
        billingService.cancelBill(billId);
        return ResponseEntity.ok("Bill cancelled successfully");
    }
}
