package lv.javaguru.travel.insurance.rest.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelGetAgreementRequestLogger {
    public static final Logger LOGGER = LoggerFactory.getLogger(TravelGetAgreementRequestLogger.class);

    void log(String uuid) {
        LOGGER.info("REQUEST: agreement uuid = " + uuid);
    }

}
