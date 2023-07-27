package io.lonmstalker.colearner.builder.interfaces;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

public interface TelegramKeyboardBuilder<T extends Serializable> extends MessageBuilder<T> {

    BotApiMethod<T>  build();

    MessageBuilder<T> toParentBuilder();
}
