package com.manning.tutorial.notification.templateformatterservice.service;

import com.manning.tutorial.notification.templateformatterservice.model.TemplateRequest;
import com.manning.tutorial.notification.templateformatterservice.model.TemplateResponse;
import com.manning.tutorial.notification.templateformatterservice.service.formatter.EmailFormatter;
import com.manning.tutorial.notification.templateformatterservice.service.formatter.SmsFormatter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TemplateService {

    private final EmailFormatter emailFormatter;
    private final SmsFormatter smsFormatter;

    public TemplateResponse format(TemplateRequest templateRequest) {
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
