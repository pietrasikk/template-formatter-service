package com.manning.tutorial.notification.templateformatterservice.service;

import com.manning.tutorial.notification.templateformatterservice.model.TemplateRequest;
import com.manning.tutorial.notification.templateformatterservice.model.TemplateResponse;
import com.manning.tutorial.notification.templateformatterservice.service.formatter.EmailFormatter;
import com.manning.tutorial.notification.templateformatterservice.service.formatter.SmsFormatter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class TemplateService {

    private final EmailFormatter emailFormatter;
    private final SmsFormatter smsFormatter;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TemplateResponse format(TemplateRequest templateRequest) {
        logger.info("In the Notification Template Formatter API Class");
        switch (templateRequest.getNotificationMode()) {
            case "EMAIL":
                return emailFormatter.format(templateRequest);
            case "SMS":
                return smsFormatter.format(templateRequest);
            default:
                return prepareErrorResponse();
        }
    }

    private TemplateResponse prepareErrorResponse() {
        return new TemplateResponse("ERROR", "There is no such notification mode", null, null, null);
    }
}
