package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;

public interface TravelGetPolicyService {
    TravelGetPolicyCoreResult getPolicy(TravelGetPolicyCoreCommand command);
}
