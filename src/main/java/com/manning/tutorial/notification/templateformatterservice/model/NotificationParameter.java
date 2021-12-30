package com.manning.tutorial.notification.templateformatterservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


@Value
public class NotificationParameter {
    String notificationParameterName;
    String notificationParameterValue;

    @JsonCreator
    NotificationParameter(@JsonProperty("notificationParameterName") String notificationParameterName,
                          @JsonProperty("notificationParameterValue") String notificationParameterValue) {
        this.notificationParameterName = notificationParameterName;
        this.notificationParameterValue = notificationParameterValue;
    }
}
