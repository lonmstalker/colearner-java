package io.lonmstalker.colearner.bot.builder;

import io.lonmstalker.colearner.bot.interfaces.TelegramApiBuilder;
import io.lonmstalker.colearner.helper.MessageHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class TelegramInlineKeyboardApiBuilder implements TelegramApiBuilder {
    final MessageHelper messageHelper;
    private final TelegramMessageBuilder builder;

    @Nullable
    private List<List<InlineKeyboardButton>> keyboard;

    public TelegramInlineApiRowBuilder addRow() {
        if (keyboard == null) {
            keyboard = new ArrayList<>();
        }
        return new TelegramInlineApiRowBuilder(this);
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
        this.builder.keyboard = InlineKeyboardMarkup.builder()
                .keyboard(this.keyboard)
                .build();
    }

    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
   public static class TelegramInlineApiRowBuilder implements TelegramApiBuilder {
        final TelegramInlineKeyboardApiBuilder builder;
        final List<InlineKeyboardButton> row = new ArrayList<>();

        public TelegramInlineApiRowBuilder addRow() {
            requireNonNull(this.builder.keyboard).add(this.row);
            return this;
        }

        public TelegramInlineApiRowBuilder addButton(final String code, final String callback) {
            this.row.add(new TelegramInlineApiButtonBuilder(this).text(code).callback(callback).toButton());
            return this;
        }

        public TelegramInlineApiButtonBuilder addButton() {
            return new TelegramInlineApiButtonBuilder(this);
        }

        @Override
        public BotApiMethod<Serializable> build() {
            this.addRow();
            return this.builder.build();
        }

        @Override
        public TelegramInlineKeyboardApiBuilder toParentBuilder() {
            this.addRow();
            return this.builder;
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TelegramInlineApiButtonBuilder implements TelegramApiBuilder {
        private final TelegramInlineApiRowBuilder builder;
        private final InlineKeyboardButton keyboardButton = new InlineKeyboardButton();

        public TelegramInlineApiRowBuilder addRow() {
            this.builder.row.add(this.keyboardButton);
            return builder;
        }

        public TelegramInlineApiButtonBuilder text(final String code) {
            this.keyboardButton.setText(this.builder.builder.messageHelper.getMessage(code));
            return this;
        }

        public TelegramInlineApiButtonBuilder url(final String url) {
            this.keyboardButton.setUrl(url);
            return this;
        }

        public TelegramInlineApiButtonBuilder callback(final String callback) {
            this.keyboardButton.setCallbackData(callback);
            return this;
        }

        public InlineKeyboardButton toButton() {
            return this.keyboardButton;
        }

        @Override
        public BotApiMethod<Serializable>build() {
            this.builder.row.add(this.keyboardButton);
            return this.builder.build();
        }

        @Override
        public TelegramInlineApiRowBuilder toParentBuilder() {
            this.builder.row.add(this.keyboardButton);
            return this.builder;
        }
    }
}
