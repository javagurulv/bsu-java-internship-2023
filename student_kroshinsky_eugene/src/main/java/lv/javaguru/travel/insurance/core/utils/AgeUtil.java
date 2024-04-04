package lv.javaguru.travel.insurance.core.utils;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Component
public class AgeUtil {
    public int calculateAge(TravelCalculatePremiumRequestV1 request) {
        LocalDate dateOfBirth = request.getPersonBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }
}
