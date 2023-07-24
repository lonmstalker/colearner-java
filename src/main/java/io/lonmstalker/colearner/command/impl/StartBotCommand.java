package io.lonmstalker.colearner.command.impl;

import io.lonmstalker.colearner.command.interfaces.MessagePublicCommand;
import io.lonmstalker.colearner.helper.MessageHelper;
import io.lonmstalker.colearner.storage.ThreadLocaleStorage;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static io.lonmstalker.colearner.constants.MessageConstants.START_MESSAGE;

@Component
@RequiredArgsConstructor
public class StartBotCommand implements MessagePublicCommand {
    private final MessageHelper messageHelper;

    @Override
    public String getCommandName() {
        return "start";
    }

    @Override
    public BotApiMethod<Message> invoke(@NonNull final Update update) {
        final var userInfo = ThreadLocaleStorage.getUserInfo();
        return SendMessage.builder()
                .chatId(userInfo.getId())
                .text(this.messageHelper.getMessage(START_MESSAGE))
                .build();
    }
}
