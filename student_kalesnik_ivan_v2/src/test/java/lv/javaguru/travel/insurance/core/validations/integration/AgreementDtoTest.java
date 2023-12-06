package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDtoTest {

    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void Test1() {
        AgreementDto agreement = AgreementDto.builder()
                .agreementDateFrom(null)
                .agreementDateTo(null)
                .country("SPAIN")
                .selectedRisks(Arrays.asList("TRAVEL_MEDICAL"))
                .persons(Arrays.asList(PersonDto.builder()
                        .personFirstName("Vasja")
                        .personLastName("Pupkin")
                        .personCode("123")
                        .personBirthDate(createDate("01.01.2000"))
                        .medicalRiskLimitLevel("LEVEL_10000")
                        .build()))
                .agreementPremium(new BigDecimal(1000))
                .build();
        List<ValidationErrorDto> errors = validator.validate(agreement);
        Assertions.assertEquals(errors.size(), 2);
        Assertions.assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_2");
        Assertions.assertEquals(errors.get(0).getDescription(), "Field agreementDateFrom must not be empty!");
        Assertions.assertEquals(errors.get(1).getErrorCode(), "ERROR_CODE_4");
        Assertions.assertEquals(errors.get(1).getDescription(), "Field agreementDateTo must not be empty!");
    }

    @Test
    public void Test2() {
        AgreementDto agreement = AgreementDto.builder()
                .agreementDateFrom(null)
                .agreementDateTo(createDate("01.01.2030"))
                .country("SPAIN")
                .selectedRisks(Arrays.asList("TRAVEL_MEDICAL"))
                .persons(Arrays.asList(PersonDto.builder()
                        .personFirstName("Vasja")
                        .personLastName("Pupkin")
                        .personCode("123")
                        .personBirthDate(createDate("01.01.2000"))
                        .medicalRiskLimitLevel("LEVEL_10000")
                        .build()))
                .agreementPremium(new BigDecimal(1000))
                .build();
        List<ValidationErrorDto> errors = validator.validate(agreement);
        Assertions.assertEquals(errors.size(), 1);
        Assertions.assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_2");
        Assertions.assertEquals(errors.get(0).getDescription(), "Field agreementDateFrom must not be empty!");
    }



    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
