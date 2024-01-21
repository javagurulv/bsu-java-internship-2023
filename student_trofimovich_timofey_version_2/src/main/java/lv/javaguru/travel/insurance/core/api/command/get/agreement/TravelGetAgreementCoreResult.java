package lv.javaguru.travel.insurance.core.api.command.get.agreement;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelGetAgreementCoreResult {

    private AgreementDTO agreement;
    private Optional<ValidationErrorDTO> error;

    public TravelGetAgreementCoreResult(ValidationErrorDTO error) {
        this.error = Optional.of(error);
    }

    public  boolean hasError() {
        return error.isPresent();
    }
}
