package lv.javaguru.travel.insurance.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CoreResponse {
    private List<ValidationError> errors;

    public boolean hasErrors() {return errors != null && !errors.isEmpty();}
}
