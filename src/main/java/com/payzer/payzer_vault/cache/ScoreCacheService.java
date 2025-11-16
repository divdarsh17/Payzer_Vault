package com.payzer.payzer_vault.cache;
import java.util.Optional;
public interface ScoreCacheService {

    Optional<Integer> getScore(Long userId, Long goalId);

    void putScore(Long userId, Long goalId, int score, long ttlSeconds);

    void evict(Long userId, Long goalId);
}