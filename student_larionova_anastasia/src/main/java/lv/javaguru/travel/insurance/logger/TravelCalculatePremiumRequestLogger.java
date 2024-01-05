package lv.javaguru.travel.insurance.logger;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

interface TravelCalculatePremiumRequestLogger {
    void logRequest(TravelCalculatePremiumRequest request);

}
