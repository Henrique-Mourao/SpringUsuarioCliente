package com.example.demo.service.fraud;

import com.example.demo.entities.Transaction;
import java.util.List;

public class CreditCardRule implements FraudRule {
    private final int weight;

    public CreditCardRule(int weight) { this.weight = weight; }

    @Override
    public int apply(Transaction data, List<String> triggeredRules) {
        if (data.isCreditCard()) {
            triggeredRules.add("Credit card transaction");
            return weight;
        }
        return 0;
    }
}

