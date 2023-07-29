package io.lonmstalker.colearner.bot.builder;

import io.lonmstalker.colearner.bot.interfaces.TelegramApiBuilder;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TelegramReplyKeyboardApiBuilder implements TelegramApiBuilder {
    final MessageHelper messageHelper;

    private final TelegramMessageBuilder builder;
    private List<KeyboardRow> keyboard;
    private boolean selective;
    private boolean resizeKeyboard;
    private boolean oneTimeKeyboard;
    private boolean isPersistent;
    private String inputFieldPlaceholder;

    public TelegramReplyApiRowBuilder addRow() {
        if (keyboard == null) {
            keyboard = new ArrayList<>();
        }
        return new TelegramReplyApiRowBuilder(this);
    }

    public TelegramReplyKeyboardApiBuilder selective(final boolean selective) {
        this.selective = selective;
        return this;
    }

    public TelegramReplyKeyboardApiBuilder resizeKeyboard(final boolean resizeKeyboard) {
        this.resizeKeyboard = resizeKeyboard;
        return this;
    }

    public TelegramReplyKeyboardApiBuilder oneTimeKeyboard(final boolean oneTimeKeyboard) {
        this.oneTimeKeyboard = oneTimeKeyboard;
        return this;
    }

    public TelegramReplyKeyboardApiBuilder persistent(final boolean persistent) {
        this.isPersistent = persistent;
        return this;
    }

    public TelegramReplyKeyboardApiBuilder inputFieldPlaceholder(final String inputFieldPlaceholder) {
        this.inputFieldPlaceholder = inputFieldPlaceholder;
        return this;
    }

    public ReplyKeyboard buildKeyboard() {
        this.setMarkup();
        return this.builder.keyboard;
    }

    @Override
    public BotApiMethod<Serializable> build() {
        this.setMarkup();
        return this.builder.build();
    }

    @Override
    public TelegramMessageBuilder toParentBuilder() {
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

    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TelegramReplyApiRowBuilder implements TelegramApiBuilder {
        private final TelegramReplyKeyboardApiBuilder builder;
        final KeyboardRow row = new KeyboardRow();

        public TelegramReplyApiRowBuilder addRow() {
            this.builder.keyboard.add(this.row);
            return this;
        }

        public TelegramReplyApiRowBuilder addButton(final String code) {
            this.row.add(new KeyboardButton(this.builder.messageHelper.getMessage(code)));
            return this;
        }

        public TelegramReplyKeyboardButtonBuilder addButton() {
            return new TelegramReplyKeyboardButtonBuilder(this);
        }

        @Override
        public BotApiMethod<Serializable> build() {
            this.addRow();
            return builder.build();
        }

        @Override
        public TelegramReplyKeyboardApiBuilder toParentBuilder() {
            this.addRow();
            return this.builder;
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TelegramReplyKeyboardButtonBuilder {
        private final TelegramReplyApiRowBuilder builder;
        private final KeyboardButton keyboardButton = new KeyboardButton();

        public TelegramReplyApiRowBuilder addRow() {
            this.builder.row.add(this.keyboardButton);
            return builder;
        }

        public KeyboardButton toButton() {
            return this.keyboardButton;
        }
    }
}
