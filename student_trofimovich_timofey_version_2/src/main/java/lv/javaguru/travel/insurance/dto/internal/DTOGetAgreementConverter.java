package lv.javaguru.travel.insurance.dto.internal;

import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.common.PersonDTOConverter;
import lv.javaguru.travel.insurance.dto.v2.PersonResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DTOGetAgreementConverter {

    @Autowired
    private PersonDTOConverter personDTOConverter;


    public TravelGetAgreementResponse buildResponse(TravelGetAgreementCoreResult coreResult) {
        return coreResult.hasError() ? buildErrorResponse(coreResult) : buildSuccessfulResponse(coreResult);

    }


    private TravelGetAgreementResponse buildErrorResponse(TravelGetAgreementCoreResult coreResult) {
        ValidationError validationError = transformValidationErrorDTO(coreResult.getError().get());
        return new TravelGetAgreementResponse(Optional.of(validationError));
    }

    private ValidationError transformValidationErrorDTO(ValidationErrorDTO error) {
        return new ValidationError(error.getErrorCode(), error.getDescription());
    }

    private TravelGetAgreementResponse buildSuccessfulResponse(TravelGetAgreementCoreResult coreResult) {
        TravelGetAgreementResponse response = new TravelGetAgreementResponse();
        AgreementDTO agreementDTO = coreResult.getAgreement();
        response.setUuid(agreementDTO.getUuid());
        response.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        response.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        response.setCountry(agreementDTO.getCountry());
        response.setAgreementPremium(agreementDTO.getAgreementPremium());

        List<PersonResponseDTO> persons = agreementDTO.getPersons().stream()
                .map(personDTOConverter::buildPersonFromResponse)
                .toList();
        response.setPersons(persons);

        response.setPersons(persons);
        return response;
    }


}
