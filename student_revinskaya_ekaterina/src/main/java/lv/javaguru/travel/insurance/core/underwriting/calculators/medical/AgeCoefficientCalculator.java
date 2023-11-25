package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AgeCoefficientCalculator {
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;
    @Autowired
    private DateTimeUtil dateTimeUtil;

    BigDecimal calculate(TravelCalculatePremiumRequest request){
        Period period = Period.between(localDateFromDate(request.getBirthday()),
                localDateFromDate(dateTimeUtil.getCurrentDateTime()));
        return ageCoefficientRepository.findByAge(period.getYears())
                .orElseThrow(()->new RuntimeException("coefficient not found for calculated age")).getCoefficient();

    }
    private LocalDate localDateFromDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
