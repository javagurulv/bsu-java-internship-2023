package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.validators.ValidationError;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoreResponse {
    private List<ValidationError> errors;

    boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
