package com.example.payment_service.kafka;

import com.example.payment_service.events.OrderSagaEvent;
import com.example.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor 
public class PaymentEventConsumer {

    private final PaymentService paymentService;

    private final PaymentEventProducer paymentEventProducer;

    @KafkaListener(
            topics = "payment-requests",
            groupId = "payment-group-debug"
    )
    public void consume(OrderSagaEvent event) {

        try {
            paymentService.processPayment(event);

            paymentEventProducer.publishPaymentCompleted(event);
        } catch (Exception e) {
            // TODO: handle exception
            paymentEventProducer.publishPaymentFailed(event);

            System.out.println("PAYMENT FAILED : " + e.getMessage());
        }
    }
}