package lv.javaguru.travel.insurance.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.calculate.premium.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.premium.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.common.PersonDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DTOV2Converter {
    @Autowired
    private PersonDTOConverter personDTOConverter;
    public TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        return coreResult.hasErrors() ? buildResponseWithErrors(coreResult) : buildSuccessfulResponse(coreResult);
    }

    private TravelCalculatePremiumResponseV2 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        AgreementDTO agreement = coreResult.getAgreement();
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());
        response.setUuid(agreement.getUuid());

        List<PersonResponseDTO> persons = agreement.getPersons().stream()
                .map(personDTOConverter::buildPersonResponse)
                .toList();
        response.setPersons(persons);
        return response;
    }


    private TravelCalculatePremiumResponseV2 buildResponseWithErrors(TravelCalculatePremiumCoreResult coreResult) {
        List<ValidationError> errors = transformValidationErrors(coreResult.getErrors());
        return new TravelCalculatePremiumResponseV2(errors);
    }

    private List<ValidationError> transformValidationErrors(List<ValidationErrorDTO> errors) {
        return errors.stream().map(error -> new ValidationError(error.getErrorCode(), error.getDescription())).toList();
    }

    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreement = buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());
        agreement.setPersons(transformPersonDTOs(request));
        return agreement;
    }

    private List<PersonDTO> transformPersonDTOs(TravelCalculatePremiumRequestV2 request) {
        if (request.getPersons() == null) {
            return List.of();
        } else {
            return request.getPersons().stream()
                    .map(person ->
                            new PersonDTO(person.getPersonUUID(), person.getPersonFirstName(), person.getPersonLastName()
                            , person.getPersonBirthDate(), null, person.getMedicalRiskLimitLevel(),
                                    person.getTravelCost()))
                    .toList();
        }
    }

}
