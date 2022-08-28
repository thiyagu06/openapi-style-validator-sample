package com.thiyagu06;

import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.openapitools.openapistylevalidator.api.Rule;
import org.openapitools.openapistylevalidator.error.StyleCheckSection;
import org.openapitools.openapistylevalidator.error.StyleError;

import java.util.ArrayList;
import java.util.List;

public class TagRule implements Rule {
    @Override
    public String ruleName() {
        return "Tag Rule";
    }

    @Override
    public String description() {
        return "Each operationId should have tag defined";
    }

    @Override
    public List<StyleError> execute(OpenAPI openAPI) {
        List<StyleError> errors = new ArrayList<>();
        System.out.println("Executing Operation has tag rule");
        for (String key : openAPI.getPaths().getPathItems().keySet()) {
            PathItem path = openAPI.getPaths().getPathItems().get(key);
            for (PathItem.HttpMethod method : path.getOperations().keySet()) {
                Operation op = path.getOperations().get(method);
                if (op.getTags() == null || op.getTags().isEmpty()) {
                    StyleError styleError = new StyleError(StyleCheckSection.APIInfo, "tags", "Operation Id= " + op.getOperationId() + " is missing tags info");
                    errors.add(styleError);
                }
            }
        }
        return errors;
    }
}
