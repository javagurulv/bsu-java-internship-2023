package lv.javaguru.travel.insurance.dto.internal;

import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;
import lv.javaguru.travel.insurance.dto.common.ConverterFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DTOInternalConverter {
    @Autowired
    private ConverterFunctions functions;

    public TravelGetPolicyResponse convert(TravelGetPolicyCoreResult result){
        if(result.hasErrors()){
            return errorResponseInternalFromCoreResult(result);
        } else {
            return successResponseInternalFromCoreResult(result);
        }
    }
    private TravelGetPolicyResponse errorResponseInternalFromCoreResult(TravelGetPolicyCoreResult result){
        return new TravelGetPolicyResponse(functions.listOfValidationErrorFromDTO(result.getErrors()));
    }
    private TravelGetPolicyResponse successResponseInternalFromCoreResult(TravelGetPolicyCoreResult result){
        TravelGetPolicyResponse response = new TravelGetPolicyResponse();
        response.setUuid(result.getAgreement().getUuid());
        response.setTravelCost(result.getAgreement().getTravelCost());
        response.setAgreementDateFrom(result.getAgreement().getAgreementDateFrom());
        response.setAgreementDateTo(result.getAgreement().getAgreementDateTo());
        response.setCountry(result.getAgreement().getCountry());
        response.setAgreementPremium(result.getAgreement().getAgreementPremium());
        response.setPersons(functions.listPersonResponseFromPersonDTO(result.getAgreement().getPersons()));
        return response;
    }



}
