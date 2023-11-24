package lv.javaguru.travel.insurance.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoreResponse {
    List<ValidationError> errors;
    boolean hasErrors(TravelCalculatePremiumRequest request){
        return errors != null && !errors.isEmpty();
    }
}
