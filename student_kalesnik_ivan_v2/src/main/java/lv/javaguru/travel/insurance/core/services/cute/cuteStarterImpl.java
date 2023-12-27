package lv.javaguru.travel.insurance.core.services.cute;

import lv.javaguru.travel.insurance.core.api.command.TravelCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.validations.UUIDValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class cuteStarterImpl implements cuteStarter {
    @Autowired
    private UUIDValidationImpl uuidVal;
    @Autowired private LoadAgreement Loader;

    @Override
    public TravelCoreResult getAgreement(TravelCoreCommand command) {
        List<ValidationErrorDto> errors = uuidVal.validate(command.getUuid());
        return errors.isEmpty()
                ? buildResponse(command.getUuid())
                : buildResponse(errors);
    }

    private TravelCoreResult buildResponse(List<ValidationErrorDto> errors) {
        return new TravelCoreResult(errors);
    }

    private TravelCoreResult buildResponse(String agreementUuid) {
        return new TravelCoreResult().builder()
                        .agreement(Loader.load(agreementUuid))
                        .build();
    }
}
