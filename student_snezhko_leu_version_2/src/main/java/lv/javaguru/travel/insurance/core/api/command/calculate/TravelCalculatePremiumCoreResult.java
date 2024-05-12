package lv.javaguru.travel.insurance.core.api.command.calculate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelCalculatePremiumCoreResult {

    private List<ValidationErrorDTO> errors;

    private AgreementDTO agreement;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
