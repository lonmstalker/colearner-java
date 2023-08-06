package io.lonmstalker.colearner.utils;

import io.lonmstalker.colearner.command.BotCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BotCommandUtils {

    // todo: поддержка других языков
    public static Map<String, BotCommand<?>> buildCommands(final List<BotCommand<?>> commands) {
        return commands.stream()
                .collect(Collectors.toMap(
                        BotCommand::getCommandName,
                        Function.identity()
                ));
    }
}
