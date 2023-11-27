package lv.javaguru.travel.insurance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CoreResponse {

    private List<ValidationError> errors;
    private  List<RiskPremium> riskPremiums;

    public CoreResponse(List<ValidationError> errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
