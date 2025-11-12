package com.payzer.payzer_vault.repository;

import com.payzer.payzer_vault.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop100ByUserIdOrderByOccurredAtDesc(Long userId);
}
