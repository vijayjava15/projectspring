package com.learn.websocket.error;

import com.learn.websocket.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Error extends BaseEntity {

    @Column(columnDefinition = "json")
    private String error;

    @Enumerated(EnumType.STRING)
    private ErrorType errorType;

    private String topicsCovered;

    private boolean isImportant;

    private boolean fullyUpdated;

    private boolean isLearned;

    private boolean isReadyToLearn;

    private String aiDescription;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getTopicsCovered() {
        return topicsCovered;
    }

    public void setTopicsCovered(String topicsCovered) {
        this.topicsCovered = topicsCovered;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isFullyUpdated() {
        return fullyUpdated;
    }

    public void setFullyUpdated(boolean fullyUpdated) {
        this.fullyUpdated = fullyUpdated;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    public boolean isReadyToLearn() {
        return isReadyToLearn;
    }

    public void setReadyToLearn(boolean readyToLearn) {
        isReadyToLearn = readyToLearn;
    }

    public String getAiDescription() {
        return aiDescription;
    }

    public void setAiDescription(String aiDescription) {
        this.aiDescription = aiDescription;
    }
}
