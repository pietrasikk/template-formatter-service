package com.manning.tutorial.notification.templateformatterservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class TemplateResponse {

    String status;
    String statusDescription;
    String emailContent;
    String smsContent;
    String emailSubject;

    @JsonCreator
    public TemplateResponse(@JsonProperty("status") String status,
                            @JsonProperty("statusDescription") String statusDescription,
                            @JsonProperty("emailContent") String emailContent,
                            @JsonProperty("smsContent") String smsContent,
                            @JsonProperty("emailSubject") String emailSubject) {
        this.status = status;
        this.statusDescription = statusDescription;
        this.emailContent = emailContent;
        this.smsContent = smsContent;
        this.emailSubject = emailSubject;
    }
}
