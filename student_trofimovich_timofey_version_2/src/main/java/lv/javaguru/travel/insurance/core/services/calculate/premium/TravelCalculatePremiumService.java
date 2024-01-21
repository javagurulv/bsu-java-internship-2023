package lv.javaguru.travel.insurance.core.services.calculate.premium;

import lv.javaguru.travel.insurance.core.api.command.calculate.premium.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.premium.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);

}
