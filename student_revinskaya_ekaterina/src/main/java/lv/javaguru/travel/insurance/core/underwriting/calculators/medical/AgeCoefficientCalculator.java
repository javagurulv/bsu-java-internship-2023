package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    @Value("${age.coefficient.enabled:false}")
    private Boolean ageCoefficientEnabled;

    BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        return ageCoefficientEnabled ? calculateValue(request) : defaultValue();
    }

    private BigDecimal calculateValue(TravelCalculatePremiumRequestV1 request) {
        Period period = Period.between(localDateFromDate(request.getBirthday()),
                localDateFromDate(dateTimeUtil.getCurrentDateTime()));
        return ageCoefficientRepository.findByAge(period.getYears())
                .orElseThrow(() -> new RuntimeException("coefficient for person with birthday "
                        + convertDateToString(request.getBirthday()) + " not found")).getCoefficient();
    }

    private BigDecimal defaultValue() {
        return BigDecimal.ONE;
    }

    private String convertDateToString(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    private LocalDate localDateFromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
