package io.lonmstalker.colearner.bot.builder;

import io.lonmstalker.colearner.bot.interfaces.BuilderCustomizer;
import io.lonmstalker.colearner.bot.interfaces.MessageBuilder;
import io.lonmstalker.colearner.exception.SystemColearnerException;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;

import static io.lonmstalker.colearner.constants.ErrorCodes.SERVER_ERROR;

@RequiredArgsConstructor
public class TelegramMessageBuilder implements MessageBuilder {
    protected ReplyKeyboard keyboard;
    protected BotApiMethod<?> apiMethod;

    private final MessageHelper messageHelper;

    public static TelegramMessageBuilder builder(final MessageHelper messageHelper) {
        return new TelegramMessageBuilder(messageHelper);
    }

    public TelegramReplyKeyboardApiBuilder replyKeyboard() {
        return new TelegramReplyKeyboardApiBuilder(this.messageHelper, this);
    }

    public TelegramInlineKeyboardApiBuilder inlineKeyboard() {
        return new TelegramInlineKeyboardApiBuilder(this.messageHelper, this);
    }

    public TelegramSendMessageApiBuilder sendMessage(final long chatId){
        return new TelegramSendMessageApiBuilder(this.messageHelper, chatId, this);
    }

    public TelegramMessageBuilder customize(final BuilderCustomizer customizer) {
        customizer.customize(this);
        return this;
    }

    public TelegramMessageBuilder keyboard(final ReplyKeyboard keyboard) {
        this.keyboard = keyboard;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BotApiMethod<Serializable> build() {
        if (apiMethod == null) {
            throw new SystemColearnerException(SERVER_ERROR, "build message without api method");
        }
        return (BotApiMethod<Serializable>) this.apiMethod;
    }
}
