package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;

@Component
class TravelCalculateDayCount {
    @Autowired
    private DateTimeUtil dateTimeUtil;

    public long calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return dateTimeUtil.getDaysBetween(agreement.getAgreementDateTo(), agreement.getAgreementDateFrom());
    }
}
