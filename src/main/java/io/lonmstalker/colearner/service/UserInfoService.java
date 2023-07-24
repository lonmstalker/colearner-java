package io.lonmstalker.colearner.service;

import io.lonmstalker.colearner.exception.SystemColearnerException;
import io.lonmstalker.colearner.helper.ClockHelper;
import io.lonmstalker.colearner.model.UserInfo;
import io.lonmstalker.colearner.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;
import java.util.UUID;

import static io.lonmstalker.colearner.constants.Constants.USERNAME_PREFIX;
import static io.lonmstalker.colearner.constants.ErrorCodes.ILLEGAL_START;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final ClockHelper clockHelper;
    private final UserInfoRepository userInfoRepository;

    public UserInfo getOrCreate(final Update update, final long chatId) {
        return this.findById(chatId)
                .orElseGet(() -> this.save(update));
    }

    private Optional<UserInfo> findById(long userId) {
        return this.userInfoRepository.findById(userId);
    }

    private UserInfo save(final Update update) {
        if (!update.hasMessage()) {
            throw new SystemColearnerException(ILLEGAL_START, "can't save when no start");
        }

        final var chat = update.getMessage().getChat();

        final var username = chat.getUserName() != null
                ? chat.getUserName()
                : USERNAME_PREFIX + UUID.randomUUID();
        final var user = UserInfo.builder()
                .id(chat.getId())
                .username(username)
                .createdDate(this.clockHelper.clockOffset())
                .build();

        return this.userInfoRepository.saveAndFlush(user);
    }
}
