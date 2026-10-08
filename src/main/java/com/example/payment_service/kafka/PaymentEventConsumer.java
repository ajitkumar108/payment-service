package com.example.payment_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.payment_service.events.OrderSagaEvent;

@Component 
public class PaymentEventConsumer {
    @KafkaListener (topics = "payment-requests",
                    groupId = "payment-group"
    )
    public void consume(OrderSagaEvent event){
        System.out.println("=======================");
        System.out.println("PAYMENT REQUEST RECIEVED");
        System.out.println("sagaId : " + event.getSagaId());
        System.out.println("orderId : " + event.getOrderId());
        System.out.println("eventType : " + event.getEventType());
        System.out.println("========================");
    }
}
