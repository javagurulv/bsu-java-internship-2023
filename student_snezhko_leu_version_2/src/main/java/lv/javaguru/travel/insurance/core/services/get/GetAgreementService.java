package lv.javaguru.travel.insurance.core.services.get;

import lv.javaguru.travel.insurance.core.api.command.get.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.get.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;

public interface GetAgreementService {
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);
}
