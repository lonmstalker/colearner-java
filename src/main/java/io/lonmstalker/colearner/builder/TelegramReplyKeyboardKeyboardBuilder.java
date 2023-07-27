package io.lonmstalker.colearner.builder;

import io.lonmstalker.colearner.builder.interfaces.MessageBuilder;
import io.lonmstalker.colearner.builder.interfaces.TelegramKeyboardBuilder;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TelegramReplyKeyboardKeyboardBuilder<T extends Serializable> implements TelegramKeyboardBuilder<T> {
    final MessageHelper messageHelper;

    private final TelegramMessageBuilder<T> builder;
    private List<KeyboardRow> keyboard;
    private boolean selective;
    private boolean resizeKeyboard;
    private boolean oneTimeKeyboard;
    private boolean isPersistent;
    private String inputFieldPlaceholder;

    public TelegramReplyKeyboardRowBuilder<T> addRow() {
        if (keyboard == null) {
            keyboard = new ArrayList<>();
        }
        return new TelegramReplyKeyboardRowBuilder<>(this);
    }

    public TelegramReplyKeyboardKeyboardBuilder<T> selective(final boolean selective) {
        this.selective = selective;
        return this;
    }

    public TelegramReplyKeyboardKeyboardBuilder<T> resizeKeyboard(final boolean resizeKeyboard) {
        this.resizeKeyboard = resizeKeyboard;
        return this;
    }

    public TelegramReplyKeyboardKeyboardBuilder<T> oneTimeKeyboard(final boolean oneTimeKeyboard) {
        this.oneTimeKeyboard = oneTimeKeyboard;
        return this;
    }

    public TelegramReplyKeyboardKeyboardBuilder<T> persistent(final boolean persistent) {
        this.isPersistent = persistent;
        return this;
    }

    public TelegramReplyKeyboardKeyboardBuilder<T> inputFieldPlaceholder(final String inputFieldPlaceholder) {
        this.inputFieldPlaceholder = inputFieldPlaceholder;
        return this;
    }

    @Override
    public BotApiMethod<T> build() {
        this.setMarkup();
        return this.builder.build();
    }

    @Override
    public MessageBuilder<T> toParentBuilder() {
        this.setMarkup();
        return this.builder;
    }

    private void setMarkup() {
        this.builder.keyboard = ReplyKeyboardMarkup.builder()
                .keyboard(this.keyboard)
                .selective(this.selective)
                .isPersistent(this.isPersistent)
                .resizeKeyboard(this.resizeKeyboard)
                .oneTimeKeyboard(this.oneTimeKeyboard)
                .inputFieldPlaceholder(this.inputFieldPlaceholder)
                .build();
    }

    @RequiredArgsConstructor
    static class TelegramReplyKeyboardRowBuilder<T extends Serializable> implements TelegramKeyboardBuilder<T> {
        private final TelegramReplyKeyboardKeyboardBuilder<T> builder;
        final KeyboardRow row = new KeyboardRow();

        public TelegramReplyKeyboardRowBuilder<T> addRow() {
            this.builder.keyboard.add(this.row);
            return this;
        }

        public TelegramReplyKeyboardRowBuilder<T> addButton(final String code) {
            this.row.add(new KeyboardButton(this.builder.messageHelper.getMessage(code)));
            return this;
        }

        public TelegramReplyKeyboardButtonBuilder<T> addButton() {
            return new TelegramReplyKeyboardButtonBuilder<>(this);
        }

        @Override
        public BotApiMethod<T> build() {
            this.addRow();
            return builder.build();
        }

        @Override
        public MessageBuilder<T> toParentBuilder() {
            return this.builder.toParentBuilder();
        }
    }

    @RequiredArgsConstructor
    static class TelegramReplyKeyboardButtonBuilder<T extends Serializable> {
        private final TelegramReplyKeyboardRowBuilder<T> builder;
        private final KeyboardButton keyboardButton = new KeyboardButton();

        public TelegramReplyKeyboardRowBuilder<T> addRow() {
            this.builder.row.add(this.keyboardButton);
            return builder;
        }

        public KeyboardButton toButton() {
            return this.keyboardButton;
        }
    }
}
