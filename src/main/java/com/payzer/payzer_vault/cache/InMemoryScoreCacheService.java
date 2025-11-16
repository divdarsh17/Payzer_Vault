package com.payzer.payzer_vault.cache;
import org.springframework.stereotype.Service;
import java.time.Instant;import java.util.Map;import java.util.Optional;import java.util.concurrent.ConcurrentHashMap;
@Service public class InMemoryScoreCacheService implements ScoreCacheService {

    private static class Value {
        final int score;
        final Instant expiresAt;

        Value(int score, Instant expiresAt) {
            this.score = score;
            this.expiresAt = expiresAt;
        }
    }

    private final Map<CacheKey, Value> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Integer> getScore(Long userId, Long goalId) {
        CacheKey key = new CacheKey(userId, goalId);
        Value value = store.get(key);
        if (value == null) {
            return Optional.empty();
        }
        if (Instant.now().isAfter(value.expiresAt)) {
            store.remove(key);
            return Optional.empty();
        }
        return Optional.of(value.score);
    }

    @Override
    public void putScore(Long userId, Long goalId, int score, long ttlSeconds) {
        CacheKey key = new CacheKey(userId, goalId);
        Instant expiresAt = Instant.now().plusSeconds(ttlSeconds);
        store.put(key, new Value(score, expiresAt));
    }

    @Override
    public void evict(Long userId, Long goalId) {
        store.remove(new CacheKey(userId, goalId));
    }
}