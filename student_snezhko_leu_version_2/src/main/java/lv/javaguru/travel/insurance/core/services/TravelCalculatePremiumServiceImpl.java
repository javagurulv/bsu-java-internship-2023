package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Component
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService{
    @Autowired
    private TravelAgreementValidator validator;

    @Autowired
    private TravelUnderwriting underwriting;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = validator.validate(command.getAgreement());
        return errors.isEmpty()
                ? buildResult(command.getAgreement())
                : buildResult(errors)
                ;
    }

    private TravelCalculatePremiumCoreResult buildResult(AgreementDTO agreement) {
        TravelCalculatePremiumCoreResult result = new TravelCalculatePremiumCoreResult();
        calculatePremiumForEachRisk(agreement);

        BigDecimal totalPremium = calculateTotalPremium(agreement);
        agreement.setAgreementPremium(totalPremium);
        result.setAgreement(agreement);
        return result;
    }

    private void calculatePremiumForEachRisk(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult result = underwriting.calculatePremium(agreement, person);
            person.setPersonRisks(result.getRisks());
            person.setPersonPremium(result.getTotalPremium());
        });
    }

    private BigDecimal calculateTotalPremium(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(PersonDTO::getPersonRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private TravelCalculatePremiumCoreResult buildResult(List<ValidationErrorDTO> errors) {
        TravelCalculatePremiumCoreResult result = new TravelCalculatePremiumCoreResult();
        result.setErrors(errors);
        return result;
    }
}
