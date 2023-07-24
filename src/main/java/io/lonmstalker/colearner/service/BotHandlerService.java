package io.lonmstalker.colearner.service;

import io.lonmstalker.colearner.command.BotCommand;
import io.lonmstalker.colearner.enums.BotMethodTypeEnum;
import io.lonmstalker.colearner.exception.ColearnerException;
import io.lonmstalker.colearner.storage.ThreadLocaleStorage;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

import static io.lonmstalker.colearner.constants.Constants.SLASH;
import static io.lonmstalker.colearner.constants.ErrorCodes.UNKNOWN_COMMAND;
import static io.lonmstalker.colearner.constants.SplitConstants.SPACE_SPLITTER;
import static io.lonmstalker.colearner.storage.ThreadLocaleStorage.setUserInfo;
import static io.lonmstalker.colearner.utils.BotCommandUtils.buildCommands;

@Slf4j
@Service
public class BotHandlerService {
    private final UserInfoService userInfoService;
    private final Map<String, BotCommand<?>> commands;

    public BotHandlerService(final UserInfoService userInfoService,
                             final List<BotCommand<?>> commands) {
        this.userInfoService = userInfoService;
        this.commands = buildCommands(commands);
    }

    public BotApiMethod<?> handle(final Update update,
                                  final BotMethodTypeEnum methodType,
                                  final long chatId) {
        try {
            final var command = this.getCommand(update, methodType);

            setUserInfo(this.userInfoService.getOrCreate(update, chatId));
            command.checkAccess();

            return command.invoke(update);
        } finally {
            ThreadLocaleStorage.clear();
        }
    }

    @NonNull
    private BotCommand<?> getCommand(final Update update,
                                     final BotMethodTypeEnum methodType) {
        if (methodType == BotMethodTypeEnum.MESSAGE) {
            return this.getMessageCommand(update);
        }

        throw new RuntimeException();
    }

    private BotCommand<?> getMessageCommand(final Update update) {
        final var message = update.getMessage().getText();

        final String command;
        if (message.startsWith(SLASH)) {
            command = SPACE_SPLITTER.splitToList(message).get(0);
        } else {
            command = ThreadLocaleStorage.getUserInfo().getCurrentPosition();
        }

        return this.getBotCommandCheckNotNull(command);
    }

    private BotCommand<?> getBotCommandCheckNotNull(final String command) {
        final var botCommand = this.commands.get(command);

        if (botCommand == null) {
            throw new ColearnerException(UNKNOWN_COMMAND, "unknown command: " + command);
        }

        return botCommand;
    }
}