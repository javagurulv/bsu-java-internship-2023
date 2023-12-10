package lv.javaguru.travel.insurance.dto.v1;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.stream.Collectors;

public class ResultBuilder {
    protected static TravelCalculatePremiumResponseV1 buildSuccessfulResponse(TravelCalculatePremiumCoreResult result) {
        AgreementDto agreement = result.getAgreement();
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        //
        response.setPersonFirstName(agreement.getPersons().get(0).getPersonFirstName());
        response.setPersonLastName(agreement.getPersons().get(0).getPersonLastName());
        response.setPersonCode(agreement.getPersons().get(0).getPersonCode());
        response.setPersonBirthDate(agreement.getPersons().get(0).getPersonBirthDate());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setMedicalRiskLimitLevel(agreement.getPersons().get(0).getMedicalRiskLimitLevel());
        response.setAgreementPremium(agreement.getAgreementPremium());
        //
        PersonDto person = agreement.getPersons().get(0);
        List<RiskPremium> risks = person.getRisks().stream().map(riskDto -> new RiskPremium(riskDto.getRiskIc(), riskDto.getPremium())).toList();
        response.setRisks(risks);
        return response;
    }

    protected static List<ValidationError> transformValidationErrorsToV1(List<ValidationErrorDto> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
                .collect(Collectors.toList());
    }

    protected static TravelCalculatePremiumResponseV1 buildResponseWithErrors(List<ValidationErrorDto> coreErrors) {
        List<ValidationError> errors = transformValidationErrorsToV1(coreErrors);
        return new TravelCalculatePremiumResponseV1(errors);
    }
}
