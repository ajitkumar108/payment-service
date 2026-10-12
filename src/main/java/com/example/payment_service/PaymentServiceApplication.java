package com.example.payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.DefaultErrorHandler;
@SpringBootApplication
@EnableKafka 
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}


@Bean
DefaultErrorHandler errorHandler() {
    return new DefaultErrorHandler(
        (record, exception) -> {
            System.out.println("KAFKA ERROR");
            System.out.println(record);
            exception.printStackTrace();
        }
    );
}
}
