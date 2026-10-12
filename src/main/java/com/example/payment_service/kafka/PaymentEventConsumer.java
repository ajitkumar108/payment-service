package com.example.payment_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

    @KafkaListener(
            topics = "payment-requests",
            groupId = "payment-group-debug"
    )
    public void consume(ConsumerRecord<?, ?> record) {

        System.out.println("********************************");
        System.out.println("TOPIC  : " + record.topic());
        System.out.println("OFFSET : " + record.offset());
        System.out.println("VALUE  : " + record.value());
        System.out.println("********************************");
    }
}