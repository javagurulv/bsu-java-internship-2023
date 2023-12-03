package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorForTotalAgreementPremium;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorRiskPremiumsForAllPersons;
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
    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private CalculatorForTotalAgreementPremium calculatorForTotalAgreementPremium;
    @Autowired
    private CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {

        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        if (!errors.isEmpty()) {
            return buildErrorResponse(errors);
        }
        return buildSuccessResponse(command.getAgreement());
    }

    private TravelCalculatePremiumCoreResult buildErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildSuccessResponse(AgreementDTO agreement) {
        calculatorRiskPremiumsForAllPersons.calculate(agreement);

        BigDecimal totalAgreementPremium = calculatorForTotalAgreementPremium.calculate(agreement);
        agreement.setAgreementPremium(totalAgreementPremium);

        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreement);
        return coreResult;
    }

}