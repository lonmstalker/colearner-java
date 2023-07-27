package io.lonmstalker.colearner.builder;

import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TelegramInlineKeyboardBotBuilder implements TelegramBotBuilder {
    final MessageHelper messageHelper;
    private List<List<InlineKeyboardButton>> keyboard;

    public TelegramInlineKeyboardRowBuilder addRow() {
        if (keyboard == null) {
            keyboard = new ArrayList<>();
        }
        return new TelegramInlineKeyboardRowBuilder(this);
    }


    @Override
    public ReplyKeyboard build() {
        return InlineKeyboardMarkup.builder()
                .keyboard(this.keyboard)
                .build();
    }

    @RequiredArgsConstructor
    static class TelegramInlineKeyboardRowBuilder implements TelegramBotBuilder {
        final TelegramInlineKeyboardBotBuilder builder;
        final List<InlineKeyboardButton> row = new ArrayList<>();

        public TelegramInlineKeyboardRowBuilder addRow() {
            this.builder.keyboard.add(this.row);
            return this;
        }

        public TelegramInlineKeyboardRowBuilder addButton(final String code, final String callback) {
            this.row.add(new TelegramInlineKeyboardButtonBuilder(this).text(code).callback(callback).toButton());
            return this;
        }

        public TelegramInlineKeyboardButtonBuilder addButton() {
            return new TelegramInlineKeyboardButtonBuilder(this);
        }

        @Override
        public ReplyKeyboard build() {
            this.addRow();
            return this.builder.build();
        }
    }

    @RequiredArgsConstructor
    static class TelegramInlineKeyboardButtonBuilder implements TelegramBotBuilder{
        private final TelegramInlineKeyboardRowBuilder builder;
        private final InlineKeyboardButton keyboardButton = new InlineKeyboardButton();

        public TelegramInlineKeyboardRowBuilder addRow() {
            this.builder.row.add(this.keyboardButton);
            return builder;
        }

        public TelegramInlineKeyboardButtonBuilder text(final String code) {
            this.keyboardButton.setText(this.builder.builder.messageHelper.getMessage(code));
            return this;
        }

        public TelegramInlineKeyboardButtonBuilder url(final String url) {
            this.keyboardButton.setUrl(url);
            return this;
        }

        public TelegramInlineKeyboardButtonBuilder callback(final String callback) {
            this.keyboardButton.setCallbackData(callback);
            return this;
        }

        public InlineKeyboardButton toButton() {
            return this.keyboardButton;
        }

        @Override
        public ReplyKeyboard build() {
            return this.builder.build();
        }
    }
}
