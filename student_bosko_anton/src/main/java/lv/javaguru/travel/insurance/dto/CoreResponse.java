package lv.javaguru.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoreResponse {
    private List<ValidationError> errors;
    public boolean hasErrors(List<ValidationError> err) {return errors != null && !errors.isEmpty();}
}
