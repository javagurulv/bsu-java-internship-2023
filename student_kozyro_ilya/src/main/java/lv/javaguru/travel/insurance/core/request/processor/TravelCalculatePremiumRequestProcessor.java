package lv.javaguru.travel.insurance.core.request.processor;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.core.validator.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.CoreRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.validation.AbstractValidator;

import java.util.ArrayList;
import java.util.Date;


public class TravelCalculatePremiumRequestProcessor extends RequestProcessor<TravelCalculatePremiumRequest, TravelCalculatePremiumResponse>{

    public TravelCalculatePremiumRequestProcessor(DateService service, TravelCalculatePremiumRequestValidator validator) {
        super(validator);
        this.dateService = service;
    }
    DateService dateService;

    @Override
    protected TravelCalculatePremiumResponse buildValidatedResponse(TravelCalculatePremiumRequest request) {
        return TravelCalculatePremiumResponse.builder()
                .personFirstName(request.getPersonFirstName())
                .personLastName(request.getPersonLastName())
                .agreementDateTo(request.getAgreementDateTo())
                .agreementDateFrom(request.getAgreementDateFrom())
                .agreementPrice(dateService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()))
                .build();
    }

    @Override
    protected TravelCalculatePremiumResponse buildErrorResponse(TravelCalculatePremiumRequest request, ArrayList<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }
}
