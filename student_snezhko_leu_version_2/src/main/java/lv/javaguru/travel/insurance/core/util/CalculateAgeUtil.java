package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class CalculateAgeUtil {
    @Autowired
    private DateTimeUtil dateTimeUtil;
    public int calculateAge(Date dateFrom) {
        LocalDate personBirthDate = toLocalDate(dateFrom);
        LocalDate now = toLocalDate(dateTimeUtil.getCurrentDateTime());
        int res = Period.between(personBirthDate, now).getYears();
        return res;
    }
    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
