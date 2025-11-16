package com.payzer.payzer_vault.cache;
import java.util.Objects;
public class CacheKey {

    private final Long userId;
    private final Long goalId;

    public CacheKey(Long userId, Long goalId) {
        this.userId = userId;
        this.goalId = goalId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGoalId() {
        return goalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.equals(userId, cacheKey.userId)
                && Objects.equals(goalId, cacheKey.goalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, goalId);
    }
}