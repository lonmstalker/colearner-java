package io.lonmstalker.colearner.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.checkerframework.checker.nullness.qual.Nullable;

@Getter
@EqualsAndHashCode(callSuper = true)
public class MarketplaceException extends RuntimeException {
    private final String code;

    @Nullable
    private final Object[] args;

    public MarketplaceException(final String code,
                                final String message) {
        super(message);
        this.code = code;
        this.args = null;
    }

    public MarketplaceException(final String code,
                                final String message,
                                final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.args = null;
    }

    public MarketplaceException(final String code,
                                final String message,
                                final Object...args) {
        super(message);
        this.code = code;
        this.args = args;
    }
}