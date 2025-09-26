package com.example.demo.repository;

import com.example.demo.entities.FraudEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudEvaluationRepository extends JpaRepository<FraudEvaluation, Long> {
}
