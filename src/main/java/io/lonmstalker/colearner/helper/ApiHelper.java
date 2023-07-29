package io.lonmstalker.colearner.helper;

import io.lonmstalker.colearner.bot.builder.TelegramMessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiHelper {
    private final MessageHelper messageHelper;

    public TelegramMessageBuilder builder(){
        return new TelegramMessageBuilder(this.messageHelper);
    }
}
