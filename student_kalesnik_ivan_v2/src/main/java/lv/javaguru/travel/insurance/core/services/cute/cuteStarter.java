package lv.javaguru.travel.insurance.core.services.cute;

import lv.javaguru.travel.insurance.core.api.command.TravelCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCoreResult;

public interface cuteStarter {
    TravelCoreResult getAgreement(TravelCoreCommand command);
}
