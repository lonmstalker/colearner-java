package io.lonmstalker.colearner.command.impl;

import io.lonmstalker.colearner.command.interfaces.MessagePublicCommand;
import io.lonmstalker.colearner.helper.ApiHelper;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

import static io.lonmstalker.colearner.bot.customizer.MyProjectsCustomizer.MY_PROJECTS_CUSTOMIZER;
import static io.lonmstalker.colearner.constants.MessageConstants.BACK;
import static io.lonmstalker.colearner.constants.MessageConstants.MY_PROJECT_BUTTON;
import static io.lonmstalker.colearner.storage.ThreadLocaleStorage.getUserInfo;

@Component
@RequiredArgsConstructor
public class BackCommand implements MessagePublicCommand {
    private final ApiHelper apiHelper;
    private final MessageHelper messageHelper;

    @Override
    public String getCommandName() {
        return this.messageHelper.getMessage(BACK);
    }

    @Override
    public BotApiMethod<Serializable> invoke(@NonNull final Update update) {
        return this.apiHelper.builder()
                .customize(MY_PROJECTS_CUSTOMIZER)
                .sendMessage(getUserInfo().getId())
                .build();
    }
}
