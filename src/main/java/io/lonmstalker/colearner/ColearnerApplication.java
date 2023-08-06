package io.lonmstalker.colearner;

import io.lonmstalker.colearner.config.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

import java.util.Locale;

import static io.lonmstalker.colearner.constants.Constants.RU_TAG;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@Import(TelegramBotStarterConfiguration.class) // https://github.com/spring-projects/spring-boot/issues/33413
public class ColearnerApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag(RU_TAG));
        SpringApplication.run(ColearnerApplication.class, args);
    }

}
