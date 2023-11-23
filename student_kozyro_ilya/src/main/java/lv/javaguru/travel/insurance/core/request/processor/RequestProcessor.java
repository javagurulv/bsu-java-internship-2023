package lv.javaguru.travel.insurance.core.request.processor;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.core.validator.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.CoreRequest;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.validation.AbstractValidator;

import java.util.ArrayList;

@AllArgsConstructor
abstract public class RequestProcessor<REQ extends CoreRequest, RESP extends CoreResponse> {

    AbstractValidator<REQ> validator;

    public RESP buildResponse(REQ request) {
        ArrayList<ValidationError> errors = validate(request);
        return errors.isEmpty() ?
                buildValidatedResponse(request) :
                buildErrorResponse(request, errors);
    }

    protected abstract RESP buildValidatedResponse(REQ request);
    protected abstract RESP buildErrorResponse(REQ request, ArrayList<ValidationError> errors);
    protected ArrayList<ValidationError> validate(REQ request) {
        return validator.validate(request);
    }
}
