package io.lonmstalker.colearner.helper;

import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import static io.lonmstalker.colearner.storage.ThreadLocaleStorage.getLocale;

@Component
@RequiredArgsConstructor
public class MessageHelper  {
    private final MessageSource messageSource;

    public String getMessage(final String code,
                             final String defaultMessage,
                             final Object... args) {
        return this.messageSource.getMessage(
                code,
                args,
                defaultMessage,
                getLocale()
        );
    }

    public String getMessage(final String code,
                             @Nullable final String defaultMessage) {
        return this.messageSource.getMessage(
                code,
                null,
                defaultMessage,
                getLocale()
        );
    }

    public String getMessage(String code) {
        return this.messageSource.getMessage(
                code,
                null,
                null,
                getLocale()
        );
    }
}
