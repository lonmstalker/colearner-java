package io.lonmstalker.colearner.config.properties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Value
@Validated
@Component
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    @NotNull
    String botToken;

    @NotNull
    String botUsername;
}
