package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class FraudEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @ElementCollection
    private List<String> triggeredRules;

    @OneToOne
    private Transaction transaction;

    public FraudEvaluation() {}

    public FraudEvaluation(int score, List<String> triggeredRules, Transaction transaction) {
        this.score = score;
        this.triggeredRules = triggeredRules;
        this.transaction = transaction;
    }

    public Long getId() { return id; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public List<String> getTriggeredRules() { return triggeredRules; }
    public void setTriggeredRules(List<String> triggeredRules) { this.triggeredRules = triggeredRules; }

    public Transaction getTransaction() { return transaction; }
    public void setTransaction(Transaction transaction) { this.transaction = transaction; }
}
