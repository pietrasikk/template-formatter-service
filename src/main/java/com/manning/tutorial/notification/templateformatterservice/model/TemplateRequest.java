package com.manning.tutorial.notification.templateformatterservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class TemplateRequest {

    List<NotificationParameter> notificationParameters;
    String notificationTemplateName;
    String notificationMode;

    @JsonCreator
    TemplateRequest(@JsonProperty("notificationParameters") List<NotificationParameter> notificationParameters,
                    @JsonProperty("notificationTemplateName") String notificationTemplateName,
                    @JsonProperty("notificationMode") String notificationMode) {
        this.notificationParameters = notificationParameters;
        this.notificationTemplateName = notificationTemplateName;
        this.notificationMode = notificationMode;
    }
}
