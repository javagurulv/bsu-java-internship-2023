package lv.javaguru.travel.insurance.core.services.calculate;

import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {
    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);
}
