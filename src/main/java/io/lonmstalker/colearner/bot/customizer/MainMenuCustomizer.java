package io.lonmstalker.colearner.bot.customizer;

import io.lonmstalker.colearner.bot.builder.TelegramMessageBuilder;
import io.lonmstalker.colearner.bot.interfaces.BuilderCustomizer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import static io.lonmstalker.colearner.constants.MessageConstants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MainMenuCustomizer implements BuilderCustomizer {
    public static MainMenuCustomizer MAIN_MENU_CUSTOMIZER = new MainMenuCustomizer();

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
                .addButton(PROPOSAL_PROJECTS_BUTTON)
                .addButton(MY_PROJECT_BUTTON)
                .addRow()
                .addButton(APPLICANTS_BUTTON)
                .addButton(ABOUT_ME_BUTTON)
                .toParentBuilder()
                .buildKeyboard();
        builder.keyboard(this.keyboard);
    }
}
