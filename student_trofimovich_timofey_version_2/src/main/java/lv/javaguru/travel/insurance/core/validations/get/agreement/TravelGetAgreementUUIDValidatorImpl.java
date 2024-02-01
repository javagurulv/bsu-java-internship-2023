package lv.javaguru.travel.insurance.core.validations.get.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class TravelGetAgreementUUIDValidatorImpl implements TravelGetAgreementUuidValidator {

    @Autowired
    private AgreementEntityRepository repository;
    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationErrorDTO> validate(String uuid) {
        Optional<AgreementEntity> optional = repository.findByUuid(uuid);
        return (optional.isPresent())
                ? Optional.empty()
                : Optional.of(factory.buildError("ERROR_CODE_18",
                List.of(new Placeholder("uuid", uuid))));
    }
}
