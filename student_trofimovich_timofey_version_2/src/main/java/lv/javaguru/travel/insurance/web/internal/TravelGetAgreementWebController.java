package lv.javaguru.travel.insurance.web.internal;

import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.services.get.agreement.TravelGetAgreementService;
import lv.javaguru.travel.insurance.dto.internal.DTOGetAgreementConverter;
import lv.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementWebController {
    @Autowired
    private TravelGetAgreementService service;
    @Autowired
    private DTOGetAgreementConverter converter;

    @GetMapping(value = "/{uuid}", produces = "text/html")
    public String getAgreement(@PathVariable String uuid, ModelMap modelMap) {
        TravelGetAgreementCoreCommand coreCommand = new TravelGetAgreementCoreCommand(uuid);
        TravelGetAgreementCoreResult coreResult = service.getAgreement(coreCommand);
        TravelGetAgreementResponse response = converter.buildResponse(coreResult);
        modelMap.addAttribute("response", response);
        modelMap.addAttribute("uuid", uuid);
        return "travel-get-agreement.html";
    }

}
