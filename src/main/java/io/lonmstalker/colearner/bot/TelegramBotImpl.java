package io.lonmstalker.colearner.bot;

import io.lonmstalker.colearner.config.properties.AppProperties;
import io.lonmstalker.colearner.exception.ColearnerException;
import io.lonmstalker.colearner.exception.SystemColearnerException;
import io.lonmstalker.colearner.helper.MessageHelper;
import io.lonmstalker.colearner.service.BotHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static io.lonmstalker.colearner.constants.ErrorCodes.SERVER_ERROR;
import static io.lonmstalker.colearner.utils.UpdateUtils.getChatId;
import static io.lonmstalker.colearner.utils.UpdateUtils.getType;

@Slf4j
@Component
public class TelegramBotImpl extends TelegramLongPollingBot {
    private final MessageHelper messageHelper;
    private final AppProperties appProperties;
    private final BotHandlerService botHandlerService;

    public TelegramBotImpl(final MessageHelper messageHelper,
                           final AppProperties appProperties,
                           final BotHandlerService botHandlerService) {
        super(appProperties.getBotToken());

        this.messageHelper = messageHelper;
        this.appProperties = appProperties;
        this.botHandlerService = botHandlerService;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        final var methodType = getType(update);
        final var chatId = getChatId(update, methodType);

        try {
            this.execute(this.botHandlerService.handle(update, methodType, chatId));
        } catch (final ColearnerException ex) {
            log.debug("business exception: ", ex);
            this.sendErrorMessage(this.createErrorMessage(ex.getCode(), ex.getMessage(), chatId));
        } catch (final SystemColearnerException ex) {
            log.error("catch ex: ", ex);
            this.sendErrorMessage(this.createErrorMessage(ex.getCode(), null, chatId));
        } catch (final Exception ex) {
            log.error("catch ex: ", ex);
            this.sendErrorMessage(this.createErrorMessage(SERVER_ERROR, null, chatId));
        }

    }

    @Override
    public void clearWebhook() {
    }

    @Override
    public String getBotUsername() {
        return this.appProperties.getBotUsername();
    }

    private SendMessage createErrorMessage(final String code,
                                           @Nullable final String message,
                                           final long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(this.messageHelper.getMessage(code, message))
                .build();
    }

    private void sendErrorMessage(final SendMessage sendMessage) {
        try {
            this.execute(sendMessage);
        } catch (final TelegramApiException ex) {
            log.error("catch ex: ", ex);
        }
    }
}
