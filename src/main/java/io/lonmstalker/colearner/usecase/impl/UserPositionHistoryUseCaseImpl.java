package io.lonmstalker.colearner.usecase.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.lonmstalker.colearner.usecase.UserPositionHistoryUseCase;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static io.lonmstalker.colearner.constants.CommandConstants.COMMAND_START;
import static io.lonmstalker.colearner.storage.ThreadLocaleStorage.getUserInfo;
import static java.util.Objects.requireNonNull;

@Component
public class UserPositionHistoryUseCaseImpl implements UserPositionHistoryUseCase {
    private static final Cache<Long, List<String>> USER_POSITION_HISTORY =
            Caffeine.newBuilder()
                    .expireAfterAccess(Duration.ofMinutes(10))
                    .build();

    @Override
    public void addStep(final String position) {
        final var userInfo = requireNonNull(getUserInfo());
        USER_POSITION_HISTORY
                .asMap()
                .computeIfAbsent(userInfo.getId(), id -> new ArrayList<>())
                .add(position);
    }

    @Override
    public String getPreviousPosition() {
        final var userInfo = requireNonNull(getUserInfo());
        final var userHistory = USER_POSITION_HISTORY.getIfPresent(userInfo.getId());

        if (userHistory == null) {
            return COMMAND_START;
        }

        final var indexCurPosition = userHistory.indexOf(userInfo.getCurrentPosition());
        return indexCurPosition == 0
                ? COMMAND_START
                : userHistory.get(indexCurPosition - 1);
    }
}
