package lv.javaguru.travel.insurance.web.v1;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v1.DtoV1Converter;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelInsuranceControllerV1 {

    @Autowired private TravelCalculatePremiumService service;
    @Autowired private DtoV1Converter dtoV1Converter;

    @GetMapping("/insurance/travel/web/v1")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV1());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV1());
        return "travel-calculate-premium-v1";
    }

    @PostMapping("/insurance/travel/web/v1")
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequestV1 request,
                              ModelMap modelMap) {
        TravelCalculatePremiumCoreCommand coreCommand = dtoV1Converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = service.calculatePremium(coreCommand);
        TravelCalculatePremiumResponseV1 response = dtoV1Converter.buildResponse(coreResult);
        modelMap.addAttribute("response", response);
        return "travel-calculate-premium-v1";
    }

}
