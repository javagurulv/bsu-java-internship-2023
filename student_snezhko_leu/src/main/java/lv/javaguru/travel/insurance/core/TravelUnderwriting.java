package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class TravelUnderwriting {
    public BigDecimal calculatePremium(TravelCalculatePremiumResponse response) {
        return getAgreementPrice(response.getAgreementDateTo(), response.getAgreementDateFrom());
    }
    public static BigDecimal getAgreementPrice(Date date1, Date date2) {
        return BigDecimal.valueOf(date1.getTime() - date2.getTime());
        //return BigDecimal.valueOf(TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS));
    }
}
