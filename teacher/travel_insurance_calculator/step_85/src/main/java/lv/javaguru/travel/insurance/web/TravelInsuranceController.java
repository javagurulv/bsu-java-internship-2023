package lv.javaguru.travel.insurance.web;

import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelInsuranceController {

    @Autowired private TravelCalculatePremiumService service;

    @GetMapping("/insurance/travel/web")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequest());
        return "travel-calculate-premium";
    }

    @PostMapping("/insurance/travel/web")
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequest request,
                              ModelMap modelMap) {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        modelMap.addAttribute("response", response);
        return "travel-calculate-premium";
    }

}
