package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ErrorManagerTest {

    @Autowired
    private ErrorManager errorManager;

    @Test
    public void dateFromIsFromThePastDescription() {
        assertEquals(
                "Field agreementDateFrom must not be from the past!",
                errorManager.getErrorDescription("ERROR_CODE_1")
        );
    }

    @Test
    public void dateFromIsEmptyDescription() {
        assertEquals(
                "Field agreementDateFrom must not be empty!",
                errorManager.getErrorDescription("ERROR_CODE_2")
        );
    }

    @Test
    public void dateToIsFromThePastDescription() {
        assertEquals(
                "Field agreementDateTo must not be from the past!",
                errorManager.getErrorDescription("ERROR_CODE_3")
        );
    }

    @Test
    public void dateToIsEmptyDescription() {
        assertEquals(
                "Field agreementDateTo must not be empty!",
                errorManager.getErrorDescription("ERROR_CODE_4")
        );
    }

    @Test
    public void datesSequenceErrorDescription() {
        assertEquals(
                "Field agreementDateTo must be after agreementDateFrom!",
                errorManager.getErrorDescription("ERROR_CODE_5")
        );
    }

    @Test
    public void risksAreNotSelectedDescription() {
        assertEquals(
                "Field selectedRisks must not be empty!",
                errorManager.getErrorDescription("ERROR_CODE_6")
        );
    }

    @Test
    public void firstNameIsEmptyDescription() {
        assertEquals(
                "Field personFirstName must not be empty!",
                errorManager.getErrorDescription("ERROR_CODE_7")
        );
    }

    @Test
    public void lastNameIsEmptyDescription() {
        assertEquals(
                "Field personLastName must not be empty!",
                errorManager.getErrorDescription("ERROR_CODE_8")
        );
    }
}
