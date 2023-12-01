package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementPrice(calculateAgreementPrice(request.getAgreementDateFrom(), request.getAgreementDateTo()));
        return response;
    }


    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static BigDecimal calculateAgreementPrice(Date from, Date to) {
        LocalDate localDateFrom = dateToLocalDate(from);
        LocalDate localDateTo = dateToLocalDate(to);
        Period period = Period.between(localDateFrom, localDateTo);
        return new BigDecimal(period.getDays());
    }

}
