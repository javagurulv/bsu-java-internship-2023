package lv.javaguru.travel.insurance.rest.controller;

import lv.javaguru.travel.insurance.dto.controller.TravelAgreementResponseGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UUIDRequest {
    private static final Logger logger = LoggerFactory.getLogger(TravelAgreementResponseGetter.class);

    void log(String uuid) {
        logger.info("REQUEST: UUID = " + uuid);
    }
}
