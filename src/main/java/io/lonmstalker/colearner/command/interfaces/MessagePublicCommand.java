package io.lonmstalker.colearner.command.interfaces;

import io.lonmstalker.colearner.enums.BotMethodTypeEnum;

import java.io.Serializable;

public interface MessagePublicCommand extends PublicBotCommand<Serializable> {

    @Override
    default BotMethodTypeEnum getType() {
        return BotMethodTypeEnum.MESSAGE;
    }
}
