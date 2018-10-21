package controllers;

import play.data.validation.Constraints;

public class SampleForm {
    @Constraints.Required
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
