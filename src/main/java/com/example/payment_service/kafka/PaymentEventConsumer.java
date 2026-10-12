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

        System.out.println("********************************");
        System.out.println("EVENT RECEIVED");
        System.out.println("SagaId  : " + event.getSagaId());
        System.out.println("OrderId : " + event.getOrderId());
        System.out.println("Type    : " + event.getEventType());
        System.out.println("Payload : " + event.getPayload());
        System.out.println("********************************");

        paymentService.processPayment(event);

        paymentEventProducer.publishPaymentCompleted(event);
    }
}