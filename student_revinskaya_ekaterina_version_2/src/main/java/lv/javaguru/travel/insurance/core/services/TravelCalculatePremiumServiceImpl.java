package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorForTotalAgreementPremium;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorRiskPremiumsForAllPersons;
import lv.javaguru.travel.insurance.core.services.savers.PremiumSaver;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
@Transactional
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private CalculatorForTotalAgreementPremium calculatorForTotalAgreementPremium;
    @Autowired
    private CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;
    @Autowired
    private PremiumSaver premiumSaver;

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

    private TravelCalculatePremiumCoreResult buildSuccessResponse(AgreementDTO agreementDTO) {
        calculatorRiskPremiumsForAllPersons.calculate(agreementDTO);
        BigDecimal totalAgreementPremium = calculatorForTotalAgreementPremium.calculate(agreementDTO);
        agreementDTO.setAgreementPremium(totalAgreementPremium);
        premiumSaver.savePremiums(agreementDTO);
        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreementDTO);
        return coreResult;
    }



}