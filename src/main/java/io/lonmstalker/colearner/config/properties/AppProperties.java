package io.lonmstalker.colearner.config.properties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    @NotNull
    String botToken;

    @NotNull
    String botUsername;
}
