package com.payzer.payzer_vault.controller;

import com.payzer.payzer_vault.dto.SavingsCommitmentRequest;
import com.payzer.payzer_vault.dto.ScoreResponse;
import com.payzer.payzer_vault.service.FeatureBuilderService;
import com.payzer.payzer_vault.service.MlClient;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/predict")
public class PredictController {

    private final FeatureBuilderService featureBuilder;
    private final MlClient mlClient;

    public PredictController(FeatureBuilderService featureBuilder, MlClient mlClient) {
        this.featureBuilder = featureBuilder;
        this.mlClient = mlClient;
    }

    @PostMapping(value = "/savings-commitment",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ScoreResponse> predict(@Valid @RequestBody SavingsCommitmentRequest req) {
        Map<String, Object> features = featureBuilder.build(req.getUserId(), req.getGoalId(), req.getLookbackDays());
        return mlClient.scoreSavingsCommitment(features);
    }
}
