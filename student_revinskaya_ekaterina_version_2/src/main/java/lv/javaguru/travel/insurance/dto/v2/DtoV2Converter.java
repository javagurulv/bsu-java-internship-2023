package lv.javaguru.travel.insurance.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.common.ConverterFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoV2Converter {
    @Autowired
    private ConverterFunctions functions;

    public TravelCalculatePremiumResponseV2 buildResponseV2fromCoreResult(TravelCalculatePremiumCoreResult result) {
        if (result.getErrors() != null) {
            return errorResponseV2FromCoreResult(result);
        }
        return successResponseV2FromCoreResult(result);
    }

    private TravelCalculatePremiumResponseV2 errorResponseV2FromCoreResult
            (TravelCalculatePremiumCoreResult result) {
        return new TravelCalculatePremiumResponseV2(functions.listOfValidationErrorFromDTO(result.getErrors()));
    }

    private TravelCalculatePremiumResponseV2 successResponseV2FromCoreResult
            (TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 responseV2 = new TravelCalculatePremiumResponseV2();
        responseV2.setUuid(result.getAgreement().getUuid());
        responseV2.setPersons(functions.listPersonResponseFromPersonDTO(result.getAgreement().getPersons()));

        responseV2.setAgreementPremium(result.getAgreement().getAgreementPremium());

        responseV2.setCountry(result.getAgreement().getCountry());
        responseV2.setAgreementDateFrom(result.getAgreement().getAgreementDateFrom());
        responseV2.setAgreementDateTo(result.getAgreement().getAgreementDateTo());

        return responseV2;
    }

    public TravelCalculatePremiumCoreCommand commandFromRequestV2(TravelCalculatePremiumRequestV2 requestV2) {
        AgreementDTO agreement = buildAgreementFromRequestV2(requestV2);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    private AgreementDTO buildAgreementFromRequestV2(TravelCalculatePremiumRequestV2 requestV2) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setCountry(requestV2.getCountry());
        agreement.setSelectedRisks(requestV2.getSelectedRisks());
        agreement.setAgreementDateFrom(requestV2.getAgreementDateFrom());
        agreement.setAgreementDateTo(requestV2.getAgreementDateTo());
        agreement.setPersons(functions.listPersonDTOFromPersonRequest(requestV2.getPersons()));
        return agreement;
    }



}
