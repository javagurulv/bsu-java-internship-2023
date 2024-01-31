package lv.javaguru.travel.insurance.core.validations.calculate.premium.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelCostValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory factory;


    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person, AgreementDTO agreementDTO) {
        if (travelCancellationRiskIsPresent(agreementDTO) && travelCostIsEmpty(person)) {
            return Optional.of(factory.buildError("ERROR_CODE_19"));
        }
        return Optional.empty();
    }

    private boolean travelCostIsEmpty(PersonDTO personDTO) {
        return personDTO.getTravelCost() == null;
    }

    private boolean travelCancellationRiskIsPresent(AgreementDTO agreementDTO) {
        List<String> risks = agreementDTO.getSelectedRisks();
        if (risks == null) return false;
        return risks.contains("TRAVEL_CANCELLATION");
    }
}
