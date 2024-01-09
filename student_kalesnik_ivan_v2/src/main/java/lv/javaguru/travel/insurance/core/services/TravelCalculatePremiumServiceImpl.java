package lv.javaguru.travel.insurance.core.services;

import com.google.common.base.Stopwatch;
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
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Component
@Transactional
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

    public TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDto> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }


    public TravelCalculatePremiumCoreResult buildResponse(AgreementDto agreement) {
        Stopwatch stopWatch = Stopwatch.createStarted();
        if (!stopWatch.isRunning()) stopWatch.start();
        calculateRiskPremiumsForAllPersons(agreement);

        BigDecimal totalAgreementPremium = calculateTotalAgreementPremium(agreement);
        agreement.setAgreementPremium(totalAgreementPremium);
        agreementEntityFactory.createAgreementEntity(agreement);
        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreement);
        TravelCalculatePremiumRequestExecutionTimeLogger logger = new TravelCalculatePremiumRequestExecutionTimeLogger();
        logger.logExecutionTime(stopWatch);
        return coreResult;
    }

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
