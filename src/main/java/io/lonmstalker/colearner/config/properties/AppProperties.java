package io.lonmstalker.colearner.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String botToken;
    private String botUsername;
}
