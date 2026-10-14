package com.example.payment_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController 
@RequestMapping ("/payments")
@Tag(name = "Payment API" , description = "Payment Management Endpoints")
public class PaymentController {
    
}

