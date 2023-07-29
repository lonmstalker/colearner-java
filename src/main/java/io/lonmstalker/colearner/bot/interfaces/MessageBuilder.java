package io.lonmstalker.colearner.bot.interfaces;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

public interface MessageBuilder {

    <T extends Serializable> BotApiMethod<T> build();
}
