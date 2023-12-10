package lv.javaguru.travel.insurance.dto.v1;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.javaguru.travel.insurance.dto.v1.ResultBuilder.buildResponseWithErrors;
import static lv.javaguru.travel.insurance.dto.v1.ResultBuilder.buildSuccessfulResponse;

@Component
public class DtoV1CONVERTER {
    public static TravelCalculatePremiumCoreCommand buildCommand(TravelCalculatePremiumRequestV1 request) {
        AgreementDto agreement = AgreementBuilder.buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public static TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult result) {
        return result.hasErrors() ? buildResponseWithErrors(result.getErrors()) : buildSuccessfulResponse(result);
    }
}
