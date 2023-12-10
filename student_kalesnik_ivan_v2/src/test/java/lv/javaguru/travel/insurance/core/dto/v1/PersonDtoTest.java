package lv.javaguru.travel.insurance.core.dto.v1;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.RiskDto;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.travel.insurance.dto.v1.PersonBuilder.buildPerson;
import static org.junit.jupiter.api.Assertions.*;

public class PersonDtoTest {

    @Test
    void testBuildPerson() {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            request.setPersonBirthDate(dateFormat.parse("1990-01-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setMedicalRiskLimitLevel("LEVEL_10000");

        PersonDto person = buildPerson(request);

        assertEquals("John", person.getPersonFirstName());
        assertEquals("Doe", person.getPersonLastName());
        try {
            assertEquals(dateFormat.parse("1990-01-01"), person.getPersonBirthDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertEquals("LEVEL_10000", person.getMedicalRiskLimitLevel());
    }

}