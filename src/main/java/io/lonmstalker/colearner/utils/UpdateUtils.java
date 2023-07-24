package io.lonmstalker.colearner.utils;

import io.lonmstalker.colearner.enums.BotMethodTypeEnum;
import io.lonmstalker.colearner.exception.ColearnerException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static io.lonmstalker.colearner.constants.ErrorCodes.UNSUPPORTED_MESSAGE_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateUtils {

    public static long getChatId(final Update update,
                                 final BotMethodTypeEnum methodType) {
        return switch (methodType) {
            case MESSAGE -> update.getMessage().getChatId();
            case CALLBACK_QUERY -> update.getCallbackQuery().getMessage().getChatId();
            case INLINE_QUERY -> update.getInlineQuery().getFrom().getId();
        };
    }

    public static BotMethodTypeEnum getType(final Update update) {
        if (update.hasMessage()) {
            return BotMethodTypeEnum.MESSAGE;
        }
        if (update.hasCallbackQuery()) {
            return BotMethodTypeEnum.CALLBACK_QUERY;
        }
        if (update.hasInlineQuery()) {
            return BotMethodTypeEnum.INLINE_QUERY;
        }

        throw new ColearnerException(UNSUPPORTED_MESSAGE_TYPE, "unsupported message");
    }
}
