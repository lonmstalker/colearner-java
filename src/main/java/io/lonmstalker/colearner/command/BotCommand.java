package io.lonmstalker.colearner.command;

import io.lonmstalker.colearner.enums.BotMethodTypeEnum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

public interface BotCommand<T extends Serializable> {
    String getCommandName();
    void checkAccess();
    BotMethodTypeEnum getType();
    BotApiMethod<T> invoke(@NonNull Update update);
}
