package lv.javaguru.travel.insurance.core;

import lombok.Setter;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;

    private final DateTimeService dateTimeService;

    TravelCalculatePremiumServiceImpl() {
        requestValidator = new TravelCalculatePremiumRequestValidator();
        dateTimeService = new DateTimeService();
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty() ? buildResponse(request) : buildResponse(errors);
    }

    TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request) {

        BigDecimal daysBetween = dateTimeService.getDifferenceInDays(
                request.getAgreementDateFrom(), request.getAgreementDateTo()
        );

        TravelCalculatePremiumResponse response =
                TravelCalculatePremiumResponse.builder().
                        personFirstName(request.getPersonFirstName()).
                        personLastName(request.getPersonLastName()).
                        agreementDateFrom(request.getAgreementDateFrom()).
                        agreementDateTo(request.getAgreementDateTo()).
                        agreementPrice(daysBetween).
                        build();

        return response;
    }

}
