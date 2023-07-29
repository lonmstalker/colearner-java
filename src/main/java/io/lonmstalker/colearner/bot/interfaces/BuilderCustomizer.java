package io.lonmstalker.colearner.bot.interfaces;

import io.lonmstalker.colearner.bot.builder.TelegramMessageBuilder;

public interface BuilderCustomizer {
    void customize(TelegramMessageBuilder builder);
}
