package io.lonmstalker.colearner.command.interfaces;

import io.lonmstalker.colearner.enums.BotMethodTypeEnum;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessagePublicCommand extends PublicBotCommand<Message> {

    @Override
    default BotMethodTypeEnum getType() {
        return BotMethodTypeEnum.MESSAGE;
    }
}
