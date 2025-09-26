package com.example.demo.service.fraud;

import com.example.demo.entities.Transaction;
import java.util.List;

public interface FraudRule {
    int apply(Transaction data, List<String> triggeredRules);
}
