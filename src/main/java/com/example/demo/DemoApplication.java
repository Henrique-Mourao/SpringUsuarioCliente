package com.example.demo;

import com.example.demo.entities.Transaction;
import com.example.demo.entities.FraudResult;
import com.example.demo.service.fraud.*;

public class DemoApplication{
    public static void main(String[] args) {
        FraudEngine engine = new FraudEngine();

        // Configura pesos das regras
        engine.addRule(new ConsecutiveErrorsRule(10));
        engine.addRule(new CreditCardRule(15));
        engine.addRule(new UnusualChannelRule(30));

        // Cria os dados da transação
        Transaction tx = new Transaction(
                3,          // consecutiveErrors
                true,       // isCreditCard
                "mobile",   // usualChannel
                "atm"       // currentChannel
        );

        // Avalia
        FraudResult result = engine.evaluate(tx);

        System.out.println("Pontuação final de risco: " + result.getScore());
        System.out.println("Regras disparadas: " + result.getTriggeredRules());
    }
}
