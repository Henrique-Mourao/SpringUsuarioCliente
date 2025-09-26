package com.example.demo.entities;

import java.util.List;

public class FraudResult {
    private final int score;
    private final List<String> triggeredRules;

    public FraudResult(int score, List<String> triggeredRules) {
        this.score = score;
        this.triggeredRules = triggeredRules;
    }

    public int getScore() { return score; }
    public List<String> getTriggeredRules() { return triggeredRules; }
}

