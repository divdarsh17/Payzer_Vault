package com.payzer.payzer_vault.service;
import com.payzer.payzer_vault.repository.UserRepository;import org.springframework.stereotype.Component;
@Component
public class UserGoalTransactionValidator {
    private final UserRepository userRepository;
    public UserGoalTransactionValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }
}
    