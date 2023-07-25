package io.lonmstalker.colearner.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCodes {
    public static String SERVER_ERROR = "server_error";
    public static String UNSUPPORTED_MESSAGE_TYPE = "message.type.unsupported";
    public static String ILLEGAL_START = "start.illegal";
    public static String UNKNOWN_COMMAND = "command.unknown";
}
