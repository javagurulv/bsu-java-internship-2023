package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.TravelUuidValidation;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UuidIsExistValidation implements TravelUuidValidation {
    @Autowired
    private AgreementRepository agreementRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(String uuid) {
        return  uuid != null && uuid.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
                && !agreementRepository.existByUuid(uuid) ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_20", List.of(new Placeholder("UUID", uuid))))
                : Optional.empty();
    }
}
