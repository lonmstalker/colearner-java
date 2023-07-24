package io.lonmstalker.colearner.config;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    @ConfigurationProperties("spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    @Bean
    public MessageSource messageSource(final MessageSourceProperties messageSourceProperties) {
        final var source = new ReloadableResourceBundleMessageSource();
        source.setDefaultEncoding(messageSourceProperties.getEncoding().name());
        source.setUseCodeAsDefaultMessage(messageSourceProperties.isUseCodeAsDefaultMessage());
        source.setBasenames(
                Arrays.stream(messageSourceProperties.getBasename()
                                .split(","))
                        .map(name -> "classpath:" + name)
                        .toArray(String[]::new)
        );
        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final var resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("ru"));
        return resolver;
    }
}