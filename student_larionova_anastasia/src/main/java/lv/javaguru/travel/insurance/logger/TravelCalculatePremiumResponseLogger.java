package lv.javaguru.travel.insurance.logger;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumResponseLogger {

    void logResponse(TravelCalculatePremiumResponse response);

}
