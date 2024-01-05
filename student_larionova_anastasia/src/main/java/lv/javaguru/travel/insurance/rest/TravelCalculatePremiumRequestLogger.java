package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

interface TravelCalculatePremiumRequestLogger {
    void logRequest(TravelCalculatePremiumRequest request);

}
