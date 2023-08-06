package io.lonmstalker.colearner.bot.customizer;

import io.lonmstalker.colearner.bot.builder.TelegramMessageBuilder;
import io.lonmstalker.colearner.bot.interfaces.BuilderCustomizer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import static io.lonmstalker.colearner.constants.MessageConstants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MyProjectsCustomizer implements BuilderCustomizer {
    public static MyProjectsCustomizer MY_PROJECTS_CUSTOMIZER = new MyProjectsCustomizer();

    @Nullable
    private ReplyKeyboard keyboard;

    @Override
    public void customize(final TelegramMessageBuilder builder) {
        if (keyboard != null) {
            builder.keyboard(this.keyboard);
            return;
        }
        this.keyboard = builder.replyKeyboard()
                .persistent(true)
                .resizeKeyboard(true)
                .selective(true)
                .addRow()
                .addButton(CREATE_PROJECT_BUTTON)
                .addRow()
                .addButton(GET_ARCHIVE_MY_PROJECTS)
                .addRow()
                .addButton(GET_MY_ACTIVE_PROJECTS)
                .toParentBuilder()
                .buildKeyboard();
        builder.keyboard(this.keyboard);
    }
}
