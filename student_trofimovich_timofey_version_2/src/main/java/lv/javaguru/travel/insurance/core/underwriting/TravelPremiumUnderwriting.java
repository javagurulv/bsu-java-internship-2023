package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;

public interface TravelPremiumUnderwriting {
    TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);
}
