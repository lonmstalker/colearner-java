package io.lonmstalker.colearner.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
@RequiredArgsConstructor
public class ClockHelper {
    private final Clock clock;

    public LocalDateTime clock() {
        return LocalDateTime.ofInstant(this.clockInstant(), ZoneId.systemDefault());
    }

    public OffsetDateTime clockOffset() {
        return OffsetDateTime.ofInstant(this.clockInstant(), ZoneId.systemDefault());
    }

    public Instant clockInstant() {
        return this.clock.instant();
    }
}