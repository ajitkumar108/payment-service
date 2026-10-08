package com.example.payment_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.payment_service.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table (name = "payments")
@Getter  
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Payment {
    @Id 
    @GeneratedValue
    private UUID id;

    private String orderId;

    private String sagaId;

    private BigDecimal amount;

    private String transactionId;

    @Enumerated (EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime createdAt;



}
