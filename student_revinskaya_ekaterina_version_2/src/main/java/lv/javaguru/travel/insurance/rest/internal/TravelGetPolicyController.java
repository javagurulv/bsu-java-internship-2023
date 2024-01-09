package lv.javaguru.travel.insurance.rest.internal;

import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelGetPolicyService;
import lv.javaguru.travel.insurance.dto.internal.DTOInternalConverter;
import lv.javaguru.travel.insurance.dto.internal.TravelGetPolicyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement/{uuid}")
class TravelGetPolicyController {
    @Autowired
    TravelGetPolicyService getPolicyService;
    @Autowired
    DTOInternalConverter dtoInternalConverter;

    @GetMapping(path = "/",
            produces = "application/json")
    public TravelGetPolicyResponse getPolicy(@PathVariable String uuid) {
        TravelGetPolicyCoreCommand command = new TravelGetPolicyCoreCommand(uuid);
        TravelGetPolicyCoreResult result = getPolicyService.getPolicy(command);
        return dtoInternalConverter.convert(result);
    }
}