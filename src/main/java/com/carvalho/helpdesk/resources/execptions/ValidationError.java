package com.carvalho.helpdesk.resources.execptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(long currentTimeMillis, int status, String error, String message, String path) {
        super.setTimestamp(currentTimeMillis);
        super.setStatus(status);
        super.setError(error);
        super.setMessage(message);
        super.setPath(path);
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
