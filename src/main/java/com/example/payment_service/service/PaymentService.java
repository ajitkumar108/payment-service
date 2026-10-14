package com.example.payment_service.service;

import com.example.payment_service.entity.Payment;
import com.example.payment_service.enums.PaymentStatus;
import com.example.payment_service.events.OrderSagaEvent;
import com.example.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment processPayment(OrderSagaEvent event) {

        BigDecimal amount = new BigDecimal(event.getPayload()
                                .get("amount")
                                .toString()
                            );


            if(amount.compareTo(new BigDecimal("5000")) > 0){
                throw new RuntimeException("Payment Failed");
            }

        Payment payment = Payment.builder()
                .orderId(event.getOrderId())
                .sagaId(event.getSagaId())
                .amount(new BigDecimal(
                        event.getPayload().get("amount").toString()
                ))
                .transactionId(UUID.randomUUID().toString())
                .status(PaymentStatus.SUCCESS)
                .createdAt(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }
}