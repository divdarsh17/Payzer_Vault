package com.payzer.payzer_vault.service;
import com.payzer.payzer_vault.dto.SavingsCommitmentRequest;import com.payzer.payzer_vault.dto.ScoreResponse;import org.springframework.stereotype.Service;import reactor.core.publisher.Mono;
import java.util.Map;
@Service 
public class PredictionOrchestratorService {

    private final FeatureBuilderService featureBuilderService;
    private final MlClient mlClient;
    private final FallbackService fallbackService;

    public PredictionOrchestratorService(FeatureBuilderService featureBuilderService,
                                         MlClient mlClient,
                                         FallbackService fallbackService) {
        this.featureBuilderService = featureBuilderService;
        this.mlClient = mlClient;
        this.fallbackService = fallbackService;
    }

   public Mono<ScoreResponse> predict(SavingsCommitmentRequest req) {
        Map<String, Object> features =
                featureBuilderService.build(req.getUserId(), req.getGoalId(), req.getLookbackDays());

        return mlClient.scoreSavingsCommitment(features)
                .onErrorResume(ex -> {
                            fallbackService.neutralScore(),
                            "fallback",
                            "fallback"                    );
                    return Mono.just(fallback);
                });
    }
}