package com.manning.tutorial.notification.templateformatterservice.config;

import com.manning.tutorial.notification.templateformatterservice.service.TemplateService;
import com.manning.tutorial.notification.templateformatterservice.service.formatter.EmailFormatter;
import com.manning.tutorial.notification.templateformatterservice.service.formatter.SmsFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;

@Configuration
class TemplateFormatterConfig {

    @Bean
    SmsFormatter smsFormatter() {
        return new SmsFormatter();
    }

    @Bean
    EmailFormatter htmlFormatter(TemplateEngine templateEngine) {
        return new EmailFormatter(templateEngine);
    }

    @Bean
    TemplateService templateService(EmailFormatter emailFormatter, SmsFormatter smsFormatter) {
        return new TemplateService(emailFormatter, smsFormatter);
    }
}
