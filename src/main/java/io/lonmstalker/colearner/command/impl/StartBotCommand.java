package io.lonmstalker.colearner.command.impl;

import io.lonmstalker.colearner.command.interfaces.MessagePublicCommand;
import io.lonmstalker.colearner.helper.ApiHelper;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

import static io.lonmstalker.colearner.bot.customizer.MainMenuCustomizer.MAIN_MENU_CUSTOMIZER;
import static io.lonmstalker.colearner.constants.MessageConstants.START_MESSAGE;
import static io.lonmstalker.colearner.storage.ThreadLocaleStorage.getUserInfo;

@Component
@RequiredArgsConstructor
public class StartBotCommand implements MessagePublicCommand {
    private final ApiHelper apiHelper;

    @Override
    public String getCommandName() {
        return "start";
    }

    @Override
    public BotApiMethod<Serializable> invoke(@NonNull final Update update) {
        return this.apiHelper.builder()
                .customize(MAIN_MENU_CUSTOMIZER)
                .sendMessage(getUserInfo().getId())
                .text(START_MESSAGE)
                .build();
    }
}
