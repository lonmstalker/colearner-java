package io.lonmstalker.colearner.service;

import io.lonmstalker.colearner.command.BotCommand;
import io.lonmstalker.colearner.enums.BotMethodTypeEnum;
import io.lonmstalker.colearner.exception.ColearnerException;
import io.lonmstalker.colearner.helper.MessageHelper;
import io.lonmstalker.colearner.storage.ThreadLocaleStorage;
import io.lonmstalker.colearner.usecase.UserPositionHistoryUseCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.lonmstalker.colearner.constants.Constants.INVISIBLE;
import static io.lonmstalker.colearner.constants.Constants.SLASH;
import static io.lonmstalker.colearner.constants.ErrorCodes.UNKNOWN_COMMAND;
import static io.lonmstalker.colearner.constants.MessageConstants.BACK;
import static io.lonmstalker.colearner.constants.SplitConstants.SPACE_SPLITTER;
import static io.lonmstalker.colearner.storage.ThreadLocaleStorage.setUserInfo;
import static io.lonmstalker.colearner.utils.BotCommandUtils.buildCommands;
import static org.apache.commons.lang3.StringUtils.replaceOnce;

@Slf4j
@Service
public class BotHandlerService {
    private final MessageHelper messageHelper;
    private final UserInfoService userInfoService;
    private final Map<String, BotCommand<?>> commands;
    private final UserPositionHistoryUseCase userPositionHistoryUseCase;

    public BotHandlerService(final UserInfoService userInfoService,
                             final List<BotCommand<?>> commands,
                             final MessageHelper messageHelper,
                             final UserPositionHistoryUseCase userPositionHistoryUseCase) {
        this.userInfoService = userInfoService;
        this.commands = buildCommands(commands);
        this.messageHelper = messageHelper;
        this.userPositionHistoryUseCase = userPositionHistoryUseCase;
    }

    public BotApiMethod<?> handle(final Update update,
                                  final BotMethodTypeEnum methodType,
                                  final long chatId) {
        final var startTime = System.nanoTime();
        try {
            setUserInfo(this.userInfoService.getOrCreate(update, chatId));

            final var command = this.getCommand(update, methodType);
            log.debug("handle command {} for user {}", command.getCommandName(), chatId);

            command.checkAccess();
            return command.invoke(update);
        } finally {
            ThreadLocaleStorage.clear();

            final var resultTime = System.nanoTime() - startTime;
            log.debug("end command. time ms={}, ns={}", TimeUnit.NANOSECONDS.toMillis(resultTime), resultTime);
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
        var addStep = true;
        var message = update.getMessage().getText();

        if (this.messageHelper.getMessage(BACK).equals(message)) {
            addStep = false;
            message = this.userPositionHistoryUseCase.getPreviousPosition();
        }

        final String command;
        if (message.startsWith(SLASH)) {
            command = SPACE_SPLITTER.splitToList(message).get(0).substring(1);
        } else if (message.startsWith("⠀")) {
            command = replaceOnce(message, INVISIBLE, StringUtils.EMPTY);
        } else {
            command = ThreadLocaleStorage.getUserInfo().getCurrentPosition();
        }

        if (addStep) {
            this.userPositionHistoryUseCase.addStep(command);
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
