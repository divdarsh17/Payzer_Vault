package com.payzer.payzer_vault.service;

import com.payzer.payzer_vault.dto.ScoreResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class MlClient {

    private final WebClient client;

    public MlClient(WebClient.Builder builder, @Value("${ml.base-url}") String baseUrl) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    public Mono<ScoreResponse> scoreSavingsCommitment(Map<String, Object> features) {
        return client.post()
                .uri("/score/savings-commitment")
                .bodyValue(features)
                .retrieve()
                .bodyToMono(ScoreResponse.class)
                .map(r -> { r.setSource("ml"); return r; });
    }
}
