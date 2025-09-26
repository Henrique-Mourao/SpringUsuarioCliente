package com.example.demo.controller;

import com.example.demo.entities.Transaction;
import com.example.demo.entities.FraudEvaluation;
import com.example.demo.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/evaluate")
    public FraudEvaluation avaliar(@RequestBody Transaction transaction) {
        return service.avaliarTransacao(transaction);
    }
}
