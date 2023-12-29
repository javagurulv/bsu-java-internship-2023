package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelUuidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TravelGetPolicyServiceImpl implements TravelGetPolicyService {
    @Autowired
    private TravelUuidValidator travelUuidValidator;

    @Autowired
    private ResponseOnEntityBuilder responseOnEntityBuilder;

    @Override
    public TravelGetPolicyCoreResult getPolicy(TravelGetPolicyCoreCommand command) {
        List<ValidationErrorDTO> errors = travelUuidValidator.validate(command.getUuid());
        if (errors.isEmpty()) {
            return buildSuccessResponse(command.getUuid());
        } else {
            return buildErrorResponse(errors);
        }
    }

    private TravelGetPolicyCoreResult buildErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelGetPolicyCoreResult(errors);
    }

    private TravelGetPolicyCoreResult buildSuccessResponse(String uuid) {
        return responseOnEntityBuilder.buildResponse(uuid);
    }
}
