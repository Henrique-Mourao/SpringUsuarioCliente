package com.example.demo.service;

import com.example.demo.entities.FraudResult;
import com.example.demo.entities.Transaction;
import com.example.demo.entities.FraudEvaluation;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.FraudEvaluationRepository;
import com.example.demo.service.fraud.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final FraudEvaluationRepository fraudEvaluationRepository;
    private final FraudEngine fraudEngine;

    public TransactionService(TransactionRepository transactionRepository,
                              FraudEvaluationRepository fraudEvaluationRepository) {
        this.transactionRepository = transactionRepository;
        this.fraudEvaluationRepository = fraudEvaluationRepository;

        // Configura regras
        this.fraudEngine = new FraudEngine();
        fraudEngine.addRule(new ConsecutiveErrorsRule(20));
        fraudEngine.addRule(new CreditCardRule(15));
        fraudEngine.addRule(new UnusualChannelRule(30));
    }

    public FraudEvaluation avaliarTransacao(Transaction transaction) {
        // Salva transação
        Transaction savedTx = transactionRepository.save(transaction);

        // Avalia risco
        FraudResult result = fraudEngine.evaluate(savedTx);

        // Salva avaliação
        FraudEvaluation evaluation = new FraudEvaluation(result.getScore(), result.getTriggeredRules(), savedTx);
        return fraudEvaluationRepository.save(evaluation);
    }
}
