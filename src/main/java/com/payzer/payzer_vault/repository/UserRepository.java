package com.payzer.payzer_vault.repository;

import com.payzer.payzer_vault.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
