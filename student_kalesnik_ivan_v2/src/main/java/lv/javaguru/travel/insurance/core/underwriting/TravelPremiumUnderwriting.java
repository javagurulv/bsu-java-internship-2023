package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;


public
interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(AgreementDto agreement, PersonDto person);

}
