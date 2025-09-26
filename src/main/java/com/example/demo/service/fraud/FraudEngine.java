package com.example.demo.service.fraud;

import com.example.demo.entities.FraudResult;
import com.example.demo.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FraudEngine {
    private final List<FraudRule> rules = new ArrayList<>();

    public void addRule(FraudRule rule) { rules.add(rule); }

    public FraudResult evaluate(Transaction data) {
        int score = 0;
        List<String> triggeredRules = new ArrayList<>();

        for (FraudRule rule : rules) {
            score += rule.apply(data, triggeredRules);
        }

        return new FraudResult(score, triggeredRules);
    }
}
