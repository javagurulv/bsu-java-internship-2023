package lv.javaguru.travel.insurance.core.api.command;

import lombok.*;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelCoreResult {

    private List<ValidationErrorDto> errors;

    private AgreementDto agreement;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public TravelCoreResult(List<ValidationErrorDto> errors) {
        this.errors = errors;
    }

}
