package com.manning.tutorial.notification.templateformatterservice.service.formatter;

import com.manning.tutorial.notification.templateformatterservice.model.NotificationParameter;
import com.manning.tutorial.notification.templateformatterservice.model.TemplateRequest;
import com.manning.tutorial.notification.templateformatterservice.model.TemplateResponse;
import lombok.RequiredArgsConstructor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmailFormatter {

    private static final String EMAIL_DIRECTORY_PATH = "./src/main/resources/templates/email/";
    private static final String HTML_SUFFIX = ".html";
    private static final String EMAIL_DIRECTORY = "./email/";
    private static final String PHONE_NUMBER_CHANGED = "PhoneNumberChanged";
    private final TemplateEngine templateEngine;

    public TemplateResponse format(TemplateRequest templateRequest) {
        Map<String, Object> notificationParametersMap = getParamsFromRequest(templateRequest);
        Context context = prepareContext(notificationParametersMap);
        File emailTemplateFile = getFile(templateRequest);
        if (emailTemplateFile.exists()) {
            String notificationContent = templateEngine.process(EMAIL_DIRECTORY + templateRequest.getNotificationTemplateName() + HTML_SUFFIX, context);
            if (templateRequest.getNotificationTemplateName().equals(PHONE_NUMBER_CHANGED)) {
                return prepareResponse("SUCCESS", "Successfully merged the template with the template parameters", notificationContent, null, "Changed phone number");
            } else {
                return prepareResponse("SUCCESS", "Successfully merged the template with the template parameters", notificationContent, null, "Your Bank Balance");
            }
        } else {
            return prepareResponse("ERROR", "Something went wrong with template", null, null, null);
        }
    }

    private TemplateResponse prepareResponse(String success, String statusDescription, String emailContent, String smsContent, String emailSubject) {
        return new TemplateResponse(success, statusDescription, emailContent, smsContent, emailSubject);
    }

    private File getFile(TemplateRequest templateRequest) {
        return new File(EMAIL_DIRECTORY_PATH + templateRequest.getNotificationTemplateName() + HTML_SUFFIX);
    }

    private Context prepareContext(Map<String, Object> notificationParametersMap) {
        Context context = new Context();
        context.setVariables(notificationParametersMap);
        return context;
    }

    private Map<String, Object> getParamsFromRequest(TemplateRequest templateRequest) {
        return templateRequest.getNotificationParameters().stream()
                .collect(Collectors.toMap(
                        NotificationParameter::getNotificationParameterName,
                        NotificationParameter::getNotificationParameterValue));
    }
}
