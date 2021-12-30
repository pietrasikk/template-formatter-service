package com.manning.tutorial.notification.templateformatterservice.service.formatter;

import com.manning.tutorial.notification.templateformatterservice.model.NotificationParameter;
import com.manning.tutorial.notification.templateformatterservice.model.TemplateRequest;
import com.manning.tutorial.notification.templateformatterservice.model.TemplateResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SmsFormatter {

    private static final String PHONE_NUMBER_CHANGED = "PhoneNumberChanged";
    private static final String VIEW_BALANCE = "ViewBalance";
    private static final String SMS_PHONE_NUMBER_CHANGED_TEMPLATE = "Hello ${name}\n"
            .concat("Welcome to the Citizen Bank\n")
            .concat("Your Phone number is changed from ${oldPhoneNumber} to ${newPhoneNumber}")
            .concat(" as of ").concat(LocalDate.now().toString()).concat("\n")
            .concat("You can login to internet banking channel to view more details on the account\n")
            .concat("Regards,").concat("Citizen Bank\n").concat("United States\n");
    private static final String SMS_VIEW_BALANCE_TEMPLATE = "Hello ${name}\n"
            .concat("Welcome to the Citizen Bank\n")
            .concat("Your balance for the account number ending with ${accountnumber} is ${balance}")
            .concat(" as of ").concat(LocalDate.now().toString()).concat("\n")
            .concat("You can login to internet banking channel to view more details on the account\n")
            .concat("Regards,").concat("Citizen Bank\n").concat("United States\n");

    public TemplateResponse format(TemplateRequest templateRequest) {
        Map<String, Object> notificationParametersMap = getParamsFromRequest(templateRequest);
        if (templateRequest.getNotificationTemplateName().equals(PHONE_NUMBER_CHANGED)) {
            String message = prepareMessage(notificationParametersMap, SMS_PHONE_NUMBER_CHANGED_TEMPLATE);
            return prepareResponse("SUCCESS", "Successfully merged the template with the template parameters", message);
        } else if (templateRequest.getNotificationTemplateName().equals(VIEW_BALANCE)) {
            String message = prepareMessage(notificationParametersMap, SMS_VIEW_BALANCE_TEMPLATE);
            return prepareResponse("SUCCESS", "Successfully merged the template with the template parameters", message);
        } else {
            return prepareResponse("ERROR", "Something went wrong with template", null);
        }
    }

    private TemplateResponse prepareResponse(String success, String statusDescription, String smsContent) {
        return new TemplateResponse(success, statusDescription, null, smsContent, null);
    }

    private String prepareMessage(Map<String, Object> notificationParametersMap, String template) {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(notificationParametersMap);
        return stringSubstitutor.replace(template);
    }

    private Map<String, Object> getParamsFromRequest(TemplateRequest templateRequest) {
        return templateRequest.getNotificationParameters().stream()
                .collect(Collectors.toMap(
                        NotificationParameter::getNotificationParameterName,
                        NotificationParameter::getNotificationParameterValue));
    }
}
