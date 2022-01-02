package com.manning.tutorial.notification.templateformatterservice.controllers;

import com.manning.tutorial.notification.templateformatterservice.model.TemplateRequest;
import com.manning.tutorial.notification.templateformatterservice.model.TemplateResponse;
import com.manning.tutorial.notification.templateformatterservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class TemplateController {

    private final TemplateService templateService;

    @PostMapping(path = "/api/notifications/templates",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TemplateResponse getNotificationTemplate(@RequestBody TemplateRequest templateRequest) {
        return templateService.format(templateRequest);
    }
}
