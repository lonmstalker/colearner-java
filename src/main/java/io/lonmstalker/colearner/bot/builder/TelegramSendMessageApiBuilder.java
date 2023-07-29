package io.lonmstalker.colearner.bot.builder;

import io.lonmstalker.colearner.bot.interfaces.MessageBuilder;
import io.lonmstalker.colearner.bot.interfaces.TelegramApiBuilder;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public class TelegramSendMessageApiBuilder implements TelegramApiBuilder {
    final MessageHelper messageHelper;

    private Integer messageThreadId;
    private String text;
    private String parseMode;
    private Boolean disableWebPagePreview;
    private Boolean disableNotification;
    private Integer replyToMessageId;
    private List<MessageEntity> entities;
    private Boolean allowSendingWithoutReply;
    private Boolean protectContent;

    private final long chatId;
    private final TelegramMessageBuilder builder;

    @Override
    public BotApiMethod<Serializable> build() {
        this.buildMessage();
        return this.builder.build();
    }

    @Override
    public MessageBuilder toParentBuilder() {
        this.buildMessage();
        return this.builder;
    }

    private void buildMessage() {
        this.builder.apiMethod = SendMessage.builder()
                .chatId(chatId)
                .replyToMessageId(replyToMessageId)
                .allowSendingWithoutReply(allowSendingWithoutReply)
                .protectContent(protectContent)
                .entities(entities)
                .messageThreadId(messageThreadId)
                .text(text)
                .parseMode(parseMode)
                .disableNotification(disableNotification)
                .disableWebPagePreview(disableWebPagePreview)
                .replyMarkup(this.builder.keyboard)
                .build();
    }

    public TelegramSendMessageApiBuilder messageThreadId(final Integer messageThreadId) {
        this.messageThreadId = messageThreadId;
        return this;
    }

    public TelegramSendMessageApiBuilder text(final String code) {
        this.text = this.messageHelper.getMessage(code);
        return this;
    }

    public TelegramSendMessageApiBuilder parseMode(final String parseMode) {
        this.parseMode = parseMode;
        return this;
    }

    public TelegramSendMessageApiBuilder disableWebPagePreview(final Boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
        return this;
    }

    public TelegramSendMessageApiBuilder disableNotification(final Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public TelegramSendMessageApiBuilder replyToMessageId(final Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public TelegramSendMessageApiBuilder entities(final List<MessageEntity> entities) {
        this.entities = entities;
        return this;
    }

    public TelegramSendMessageApiBuilder allowSendingWithoutReply(final Boolean allowSendingWithoutReply) {
        this.allowSendingWithoutReply = allowSendingWithoutReply;
        return this;
    }

    public TelegramSendMessageApiBuilder protectContent(final Boolean protectContent) {
        this.protectContent = protectContent;
        return this;
    }
}
