package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorCodeUtilTest {

    private ErrorCodeUtil errorCodeUtil;

    public ErrorCodeUtilTest() throws IOException {
        errorCodeUtil = new ErrorCodeUtil();
    }

    @Test
    public void dateFromIsFromThePastDescription() {
        assertEquals(
                "Field agreementDateFrom must not be from the past!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_1")
        );
    }

    @Test
    public void dateFromIsEmptyDescription() {
        assertEquals(
                "Field agreementDateFrom must not be empty!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_2")
        );
    }

    @Test
    public void dateToIsFromThePastDescription() {
        assertEquals(
                "Field agreementDateTo must not be from the past!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_3")
        );
    }

    @Test
    public void dateToIsEmptyDescription() {
        assertEquals(
                "Field agreementDateTo must not be empty!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_4")
        );
    }

    @Test
    public void datesSequenceErrorDescription() {
        assertEquals(
                "Field agreementDateTo must be after agreementDateFrom!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_5")
        );
    }

    @Test
    public void risksAreNotSelectedDescription() {
        assertEquals(
                "Field selectedRisks must not be empty!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_6")
        );
    }

    @Test
    public void firstNameIsEmptyDescription() {
        assertEquals(
                "Field personFirstName must not be empty!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_7")
        );
    }

    @Test
    public void lastNameIsEmptyDescription() {
        assertEquals(
                "Field personLastName must not be empty!",
                errorCodeUtil.getErrorDescription("ERROR_CODE_8")
        );
    }
}
