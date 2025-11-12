package com.payzer.payzer_vault.dto;

public class ScoreResponse {

    private int score;
    private String modelVersion;
    private String source;

    public ScoreResponse() {}

    public ScoreResponse(int score, String modelVersion, String source) {
        this.score = score;
        this.modelVersion = modelVersion;
        this.source = source;
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getModelVersion() { return modelVersion; }
    public void setModelVersion(String modelVersion) { this.modelVersion = modelVersion; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
