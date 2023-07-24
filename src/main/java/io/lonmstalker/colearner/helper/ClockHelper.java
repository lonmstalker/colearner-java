package io.lonmstalker.colearner.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class ClockHelper {
    private final Clock clock;

    public LocalDateTime clock() {
        return LocalDateTime.ofInstant(this.clockInstant(), ZoneId.systemDefault());
    }

    public Instant clockInstant() {
        return this.clock.instant();
    }
}