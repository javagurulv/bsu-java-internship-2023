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
    List<ValidationError> errors;
    public boolean hasErrors(TravelCalculatePremiumRequest request){
        return errors != null && !errors.isEmpty();
    }
}
