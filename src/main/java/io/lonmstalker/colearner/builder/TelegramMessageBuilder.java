package io.lonmstalker.colearner.builder;

import io.lonmstalker.colearner.builder.interfaces.MessageBuilder;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;

@RequiredArgsConstructor
public class TelegramMessageBuilder<T extends Serializable> implements MessageBuilder<T> {
    protected ReplyKeyboard keyboard;
    private final long chatId;
    private final MessageHelper messageHelper;

    public static <T extends Serializable> TelegramMessageBuilder<T> builder(final MessageHelper messageHelper,
                                                                             final long chatId) {
        return new TelegramMessageBuilder<>(chatId, messageHelper);
    }

    public TelegramReplyKeyboardKeyboardBuilder<T> replyKeyboard() {
        return new TelegramReplyKeyboardKeyboardBuilder<>(this.messageHelper, this);
    }

    public TelegramInlineKeyboardKeyboardBuilder<T> inlineKeyboard() {
        return new TelegramInlineKeyboardKeyboardBuilder<>(this.messageHelper, this);
    }

    @Override
    public BotApiMethod<T> build() {
        return null;
    }
}
