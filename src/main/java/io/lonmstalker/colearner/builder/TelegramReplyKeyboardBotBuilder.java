package io.lonmstalker.colearner.builder;

import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TelegramReplyKeyboardBotBuilder implements TelegramBotBuilder {
    final MessageHelper messageHelper;

    private List<KeyboardRow> keyboard;
    private boolean selective;
    private boolean resizeKeyboard;
    private boolean oneTimeKeyboard;
    private boolean isPersistent;
    private String inputFieldPlaceholder;

    public TelegramReplyKeyboardRowBuilder addRow() {
        if (keyboard == null) {
            keyboard = new ArrayList<>();
        }
        return new TelegramReplyKeyboardRowBuilder(this);
    }

    public TelegramReplyKeyboardBotBuilder selective(final boolean selective) {
        this.selective = selective;
        return this;
    }

    public TelegramReplyKeyboardBotBuilder resizeKeyboard(final boolean resizeKeyboard) {
        this.resizeKeyboard = resizeKeyboard;
        return this;
    }

    public TelegramReplyKeyboardBotBuilder oneTimeKeyboard(final boolean oneTimeKeyboard) {
        this.oneTimeKeyboard = oneTimeKeyboard;
        return this;
    }

    public TelegramReplyKeyboardBotBuilder persistent(final boolean persistent) {
        this.isPersistent = persistent;
        return this;
    }

    public TelegramReplyKeyboardBotBuilder inputFieldPlaceholder(final String inputFieldPlaceholder) {
        this.inputFieldPlaceholder = inputFieldPlaceholder;
        return this;
    }

    @Override
    public ReplyKeyboard build() {
        return ReplyKeyboardMarkup.builder()
                .keyboard(this.keyboard)
                .selective(this.selective)
                .isPersistent(this.isPersistent)
                .resizeKeyboard(this.resizeKeyboard)
                .oneTimeKeyboard(this.oneTimeKeyboard)
                .inputFieldPlaceholder(this.inputFieldPlaceholder)
                .build();
    }

    @RequiredArgsConstructor
    static class TelegramReplyKeyboardRowBuilder implements TelegramBotBuilder {
        private final TelegramReplyKeyboardBotBuilder builder;
        final KeyboardRow row = new KeyboardRow();

        public TelegramReplyKeyboardRowBuilder addRow() {
            this.builder.keyboard.add(this.row);
            return this;
        }

        public TelegramReplyKeyboardRowBuilder addButton(final String code) {
            this.row.add(new KeyboardButton(this.builder.messageHelper.getMessage(code)));
            return this;
        }

        public TelegramReplyKeyboardButtonBuilder addButton() {
            return new TelegramReplyKeyboardButtonBuilder(this);
        }

        @Override
        public ReplyKeyboard build() {
            this.addRow();
            return builder.build();
        }
    }

    @RequiredArgsConstructor
    static class TelegramReplyKeyboardButtonBuilder {
        private final TelegramReplyKeyboardRowBuilder builder;
        private final KeyboardButton keyboardButton = new KeyboardButton();

        public TelegramReplyKeyboardRowBuilder addRow() {
            this.builder.row.add(this.keyboardButton);
            return builder;
        }

        public KeyboardButton toButton() {
            return this.keyboardButton;
        }
    }
}
