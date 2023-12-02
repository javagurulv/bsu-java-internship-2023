package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    @Autowired
    private TravelPremiumUnderwriting underwriting = new TravelPremiumUnderwriting();

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);
        return !errors.isEmpty() ? buildResponse(errors) : buildResponse(request, underwriting.calculatePremium(request));
    }

    public TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    public TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal premium) {
        TravelCalculatePremiumResponse tempResponse = new TravelCalculatePremiumResponse();

        tempResponse.setPersonFirstName(request.getPersonFirstName());
        tempResponse.setPersonLastName(request.getPersonLastName());
        tempResponse.setAgreementDateFrom(request.getAgreementDateFrom());
        tempResponse.setAgreementDateTo(request.getAgreementDateTo());
        tempResponse.setAgreementPrice(premium);
        return tempResponse;
    }
}
