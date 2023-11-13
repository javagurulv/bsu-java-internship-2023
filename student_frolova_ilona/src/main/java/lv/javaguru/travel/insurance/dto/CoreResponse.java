package lv.javaguru.travel.insurance.dto;

import java.util.List;

public class CoreResponse {

    List<ValidationError> errors;

    public boolean hasErrors() {
        return (errors != null && !errors.isEmpty());
    }
}
