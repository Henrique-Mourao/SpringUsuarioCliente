package com.example.demo.service.fraud;

import com.example.demo.entities.Transaction;

import java.util.List;

public class ConsecutiveErrorsRule implements FraudRule {
    private final int weight;

    public ConsecutiveErrorsRule(int weight) { this.weight = weight; }

    @Override
    public int apply(Transaction data, List<String> triggeredRules) {
        if (data.getConsecutiveErrors() >= 3) {
            triggeredRules.add("Three consecutive errors (Password/CVV)");
            return weight;
        }
        return 0;
    }
}


