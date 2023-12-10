package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.repositories.api.AgreementRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UUIDValidationImpl implements UUIDValidation{
    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired private AgreementRepository repo;

    public List<ValidationErrorDto> validate(String uuid) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        if (uuid == null) {
            errors.add(errorFactory.buildError("ERROR_CODE_17"));
        } else {
            if (repo.findByUuid(uuid).isEmpty()) {
                Placeholder placeholder = new Placeholder("AGREEMENT_UUID", uuid);
                errors.add(errorFactory.buildError("ERROR_CODE_18", List.of(placeholder)));
            }
        }
        return errors;
    }
}
