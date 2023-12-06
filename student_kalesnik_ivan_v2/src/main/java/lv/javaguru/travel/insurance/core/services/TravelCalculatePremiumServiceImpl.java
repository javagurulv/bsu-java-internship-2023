package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.*;
import lv.javaguru.travel.insurance.core.services.factory.AgreementManagerFactorySaver;
import lv.javaguru.travel.insurance.core.services.factory.CommonFactorySaver;
import lv.javaguru.travel.insurance.core.services.factory.EntityManagerFactorySaver;
import lv.javaguru.travel.insurance.core.services.factory.RiskManagerFactorySaver;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired private TravelAgreementValidator agreementValidator;
    @Autowired private TravelPremiumUnderwriting premiumUnderwriting;
    @Autowired private CommonFactorySaver agreementEntityFactory;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDto> errors = agreementValidator.validate(command.getAgreement());
        return errors.isEmpty()
                ? buildResponse(command.getAgreement())
                : buildResponse(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDto> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(AgreementDto agreement) {
        calculateRiskPremiumsForAllPersons(agreement);

        BigDecimal totalAgreementPremium = calculateTotalAgreementPremium(agreement);
        agreement.setAgreementPremium(totalAgreementPremium);
        agreementEntityFactory.createAgreementEntity(agreement);
        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreement);
        return coreResult;
    }

    /* private void saveAllPersons(AgreementDto agreement) {
        agreement.getPersons().forEach(personDTO -> saver.savePerson(personDTO));
    }*/

    private void calculateRiskPremiumsForAllPersons(AgreementDto agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = premiumUnderwriting.calculatePremium(agreement, person);
            person.setRisks(calculationResult.getRiskPremiums());
        });
    }

    private BigDecimal calculateTotalAgreementPremium(AgreementDto agreement) {
        return agreement.getPersons().stream()
                .map(PersonDto::getRisks)
                .flatMap(Collection::stream)
                .map(RiskDto::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
