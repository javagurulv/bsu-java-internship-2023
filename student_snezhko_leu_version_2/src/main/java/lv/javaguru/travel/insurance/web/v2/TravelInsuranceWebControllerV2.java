package lv.javaguru.travel.insurance.web.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.v2.ConverterV2DTO;
import lv.javaguru.travel.insurance.core.api.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.core.api.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelInsuranceWebControllerV2 {
    @Autowired
    private TravelCalculatePremiumService service;

    /*
    .NullPointerException:
    Cannot invoke "java.util.List.stream()"
    because the return value of
    "lv.javaguru.travel.insurance.core.api.dto.v2.
        PersonRequestV2DTO.getSelectedRisks()" is null
     */
    @GetMapping("insurance/travel/web/v2")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV2());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV2());

        return "travel-calculate-premium-v2";
    }

    @PostMapping("insurance/travel/web/v2")
    public String processForm(@ModelAttribute("request")TravelCalculatePremiumRequestV2 request,
                              ModelMap modelMap) {
        TravelCalculatePremiumCoreCommand command = ConverterV2DTO.buildCommand(request);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        TravelCalculatePremiumResponseV2 response = ConverterV2DTO.buildResponse(result);
        modelMap.addAttribute("request", request);
        modelMap.addAttribute("response", response);

        return "travel-calculate-premium-v2";
    }
}
