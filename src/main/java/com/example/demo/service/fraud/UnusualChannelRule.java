package com.example.demo.service.fraud;

import com.example.demo.entities.Transaction;
import java.util.List;

public class UnusualChannelRule implements FraudRule {
    private final int weight;

    public UnusualChannelRule(int weight) { this.weight = weight; }

    @Override
    public int apply(Transaction data, List<String> triggeredRules) {
        if (!data.getUsualChannel().equalsIgnoreCase(data.getCurrentChannel())) {
            triggeredRules.add("Unusual channel usage");
            return weight;
        }
        return 0;
    }
}