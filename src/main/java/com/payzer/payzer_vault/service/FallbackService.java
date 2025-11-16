package com.payzer.payzer_vault.service;
import org.springframework.stereotype.Service;
@Service 
public class FallbackService {

    private static final int NEUTRAL_SCORE = 50;

    public int neutralScore() {
        return NEUTRAL_SCORE;
    }
}