package io.lonmstalker.colearner.command.interfaces;

import io.lonmstalker.colearner.command.BotCommand;

import java.io.Serializable;

public interface PublicBotCommand<T extends Serializable> extends BotCommand<T> {

    @Override
    default void checkAccess() {
    }
}
