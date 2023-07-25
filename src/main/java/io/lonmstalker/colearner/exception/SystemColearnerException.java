package io.lonmstalker.colearner.exception;

import lombok.Getter;

@Getter
public class SystemColearnerException extends RuntimeException {
    private final String code;

    public SystemColearnerException(final String code,
                                    final String message) {
        super(message);
        this.code = code;
    }

    public SystemColearnerException(final String code,
                                    final String message,
                                    final Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}