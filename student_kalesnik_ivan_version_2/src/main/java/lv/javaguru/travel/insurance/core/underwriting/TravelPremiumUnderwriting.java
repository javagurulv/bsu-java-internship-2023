package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);

}
