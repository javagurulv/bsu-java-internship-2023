package lv.javaguru.travel.insurance.core.services.get.agreement;

import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreResult;

public interface TravelGetAgreementService {
    TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);
}
