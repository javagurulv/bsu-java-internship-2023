package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    @Autowired
    TravelCalculatePremiumServiceUnderwriting underwriting = new TravelCalculatePremiumServiceUnderwriting();

    @Autowired
    public TravelCalculatePremiumServiceImpl(TravelCalculatePremiumRequestValidator requestValidator) {
        this.validator = requestValidator;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);
        return buildResponse(request, errors);

    }
    public TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, List<ValidationError> errors) {
        if (!errors.isEmpty()) {
            return new TravelCalculatePremiumResponse(errors);
        }

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementPrice(underwriting
                .calculatePremium(request.getAgreementDateFrom(), request.getAgreementDateTo()));
        return response;
    }


   /* private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static BigDecimal calculateAgreementPrice(Date from, Date to) {
        LocalDate localDateFrom = dateToLocalDate(from);
        LocalDate localDateTo = dateToLocalDate(to);
        Period period = Period.between(localDateFrom, localDateTo);
        return new BigDecimal(period.getDays());
    }*/
}
