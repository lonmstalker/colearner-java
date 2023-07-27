package io.lonmstalker.colearner.builder;

import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@RequiredArgsConstructor
public class TelegramMessageBuilder implements TelegramBotBuilder {
    private final MessageHelper messageHelper;

    public static TelegramMessageBuilder builder(final MessageHelper messageHelper) {
        return new TelegramMessageBuilder(messageHelper);
    }

    public TelegramReplyKeyboardBotBuilder replyKeyboard() {
        return new TelegramReplyKeyboardBotBuilder(this.messageHelper);
    }

    public TelegramInlineKeyboardBotBuilder inlineKeyboard() {
        return new TelegramInlineKeyboardBotBuilder(this.messageHelper);
    }

    @Override
    public ReplyKeyboard build() {
        return new ReplyKeyboardMarkup();
    }

}
