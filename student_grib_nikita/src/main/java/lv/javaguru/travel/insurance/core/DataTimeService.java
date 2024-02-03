package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Component
public class DataTimeService {
    public long getDaysBetween(Date dateFrom, Date dateTo){
        long daysDiff = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(daysDiff, TimeUnit.MILLISECONDS);
    }
}
