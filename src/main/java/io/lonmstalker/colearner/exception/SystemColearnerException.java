package io.lonmstalker.colearner.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class SystemColearnerException extends RuntimeException {
    String code;

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