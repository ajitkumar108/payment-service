package com.example.payment_service.repository;
import com.example.payment_service.entity.Payment;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    
}
