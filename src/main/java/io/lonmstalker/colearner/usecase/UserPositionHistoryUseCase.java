package io.lonmstalker.colearner.usecase;

public interface UserPositionHistoryUseCase {

    void addStep(String position);
    String getPreviousPosition();
}
