package io.lonmstalker.colearner.builder;

import io.lonmstalker.colearner.builder.interfaces.MessageBuilder;
import io.lonmstalker.colearner.builder.interfaces.TelegramKeyboardBuilder;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TelegramInlineKeyboardKeyboardBuilder<T extends Serializable> implements TelegramKeyboardBuilder<T> {
    final MessageHelper messageHelper;
    private final TelegramMessageBuilder<T> builder;
    private List<List<InlineKeyboardButton>> keyboard;

    public TelegramInlineKeyboardRowBuilder<T> addRow() {
        if (keyboard == null) {
            keyboard = new ArrayList<>();
        }
        return new TelegramInlineKeyboardRowBuilder<>(this);
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
        this.builder.keyboard = InlineKeyboardMarkup.builder()
                .keyboard(this.keyboard)
                .build();
    }

    @RequiredArgsConstructor
    static class TelegramInlineKeyboardRowBuilder<T extends Serializable> implements TelegramKeyboardBuilder<T> {
        final TelegramInlineKeyboardKeyboardBuilder<T> builder;
        final List<InlineKeyboardButton> row = new ArrayList<>();

        public TelegramInlineKeyboardRowBuilder<T> addRow() {
            this.builder.keyboard.add(this.row);
            return this;
        }

        public TelegramInlineKeyboardRowBuilder<T> addButton(final String code, final String callback) {
            this.row.add(new TelegramInlineKeyboardButtonBuilder<>(this).text(code).callback(callback).toButton());
            return this;
        }

        public TelegramInlineKeyboardButtonBuilder<T> addButton() {
            return new TelegramInlineKeyboardButtonBuilder<>(this);
        }

        @Override
        public BotApiMethod<T> build() {
            this.addRow();
            return this.builder.build();
        }

        @Override
        public MessageBuilder<T> toParentBuilder() {
            return this.builder.toParentBuilder();
        }
    }

    @RequiredArgsConstructor
    static class TelegramInlineKeyboardButtonBuilder<T extends Serializable> implements TelegramKeyboardBuilder<T> {
        private final TelegramInlineKeyboardRowBuilder<T> builder;
        private final InlineKeyboardButton keyboardButton = new InlineKeyboardButton();

        public TelegramInlineKeyboardRowBuilder<T> addRow() {
            this.builder.row.add(this.keyboardButton);
            return builder;
        }

        public TelegramInlineKeyboardButtonBuilder<T> text(final String code) {
            this.keyboardButton.setText(this.builder.builder.messageHelper.getMessage(code));
            return this;
        }

        public TelegramInlineKeyboardButtonBuilder<T> url(final String url) {
            this.keyboardButton.setUrl(url);
            return this;
        }

        public TelegramInlineKeyboardButtonBuilder<T> callback(final String callback) {
            this.keyboardButton.setCallbackData(callback);
            return this;
        }

        public InlineKeyboardButton toButton() {
            return this.keyboardButton;
        }

        @Override
        public BotApiMethod<T> build() {
            return this.builder.build();
        }

        @Override
        public MessageBuilder<T> toParentBuilder() {
            return this.builder.toParentBuilder();
        }
    }
}
