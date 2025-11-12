package com.payzer.payzer_vault.service;

import com.payzer.payzer_vault.domain.Goal;
import com.payzer.payzer_vault.domain.Transaction;
import com.payzer.payzer_vault.domain.User;
import com.payzer.payzer_vault.repository.GoalRepository;
import com.payzer.payzer_vault.repository.TransactionRepository;
import com.payzer.payzer_vault.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeatureBuilderService {

    private final UserRepository userRepo;
    private final GoalRepository goalRepo;
    private final TransactionRepository txnRepo;

    public FeatureBuilderService(UserRepository userRepo, GoalRepository goalRepo, TransactionRepository txnRepo) {
        this.userRepo = userRepo;
        this.goalRepo = goalRepo;
        this.txnRepo = txnRepo;
    }

    public Map<String, Object> build(Long userId, Long goalId, int lookbackDays) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Goal goal = goalRepo.findById(goalId)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found: " + goalId));

        List<Transaction> recent = txnRepo.findTop100ByUserIdOrderByOccurredAtDesc(userId);

        BigDecimal inflow = recent.stream()
                .filter(t -> "CREDIT".equalsIgnoreCase(t.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal outflow = recent.stream()
                .filter(t -> "DEBIT".equalsIgnoreCase(t.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> f = new HashMap<>();
        f.put("user_id", user.getId());
        f.put("user_segment", user.getSegment());
        f.put("goal_id", goal.getId());
        f.put("goal_target_amount", goal.getTargetAmount());
        f.put("txn_inflow_100", inflow);
        f.put("txn_outflow_100", outflow);
        f.put("lookback_days", lookbackDays);
        return f;
    }
}
