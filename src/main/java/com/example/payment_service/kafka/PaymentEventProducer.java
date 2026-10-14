package com.example.payment_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.payment_service.events.OrderSagaEvent;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class PaymentEventProducer {
    private final KafkaTemplate<String, OrderSagaEvent> kafakaTemplate;

    private static final String PAYMENT_RESPONSE_TOPIC = "payment-responses";

    public void publishPaymentCompleted(OrderSagaEvent event){
        OrderSagaEvent completedEvent = 
                    OrderSagaEvent.builder()
                            .sagaId(event.getSagaId())
                            .orderId(event.getOrderId())
                            .eventType("PAYMENT_COMPLETED")
                            .timestamp(java.time.LocalDateTime.now())
                            .payload(event.getPayload())
                            .build();

        kafakaTemplate.send(PAYMENT_RESPONSE_TOPIC, completedEvent);

        System.out.println("PAYMENT_COMPLETED Event Published");
    }

    public void publishPaymentFailed(OrderSagaEvent event){
    OrderSagaEvent failedEvent = 
                    OrderSagaEvent.builder()
                            .sagaId(event.getSagaId())
                            .orderId(event.getOrderId())
                            .eventType("PAYMENT_FAILED")
                            .timestamp(java.time.LocalDateTime.now())
                            .payload(event.getPayload())
                            .build();

        kafakaTemplate.send(PAYMENT_RESPONSE_TOPIC, failedEvent);

        System.out.println("PAYMENT_FAILED Event Published");
    }
}
