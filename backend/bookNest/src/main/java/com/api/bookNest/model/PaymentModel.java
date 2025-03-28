package com.api.bookNest.model;

import com.api.bookNest.model.EnumModels.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentModel {
    @Id
    @Column(name="payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserModel user;

    @Column(nullable = false)
    private String stripePaymentId; // Stripe's Payment ID

    @Column(nullable = false)
    private String subscriptionPlan;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt ;

    @PrePersist
    private void saveLocalTime(){
        this.createdAt= LocalDateTime.now();
    }
}
