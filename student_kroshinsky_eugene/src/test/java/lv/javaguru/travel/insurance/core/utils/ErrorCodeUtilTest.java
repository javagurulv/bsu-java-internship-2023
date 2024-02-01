package lv.javaguru.travel.insurance.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ErrorCodeUtilTest {
    ErrorCodeUtil errorCodeUtil = new ErrorCodeUtil();
    @Test
    void getPropertyError1() {
        assertEquals("DateTo must be greater than DateFrom!", errorCodeUtil.getProperty("ERROR_CODE_1"));
    }
    @Test
    void getPropertyError2() {
        assertEquals("Date from past!", errorCodeUtil.getProperty("ERROR_CODE_2"));
    }
    @Test
    void getPropertyError3() {
        assertEquals("Field agreementDateFrom is empty!", errorCodeUtil.getProperty("ERROR_CODE_3"));
    }
    @Test
    void getPropertyError4() {
        assertEquals("Field agreementDateTo is empty!", errorCodeUtil.getProperty("ERROR_CODE_4"));
    }
    @Test
    void getPropertyError5() {
        assertEquals("Field personFirstName is empty!", errorCodeUtil.getProperty("ERROR_CODE_5"));
    }
    @Test
    void getPropertyError6() {
        assertEquals("Field personLastName is empty!", errorCodeUtil.getProperty("ERROR_CODE_6"));
    }
    @Test
    void getPropertyError7() {
        assertEquals("Field selectedRisk is empty!", errorCodeUtil.getProperty("ERROR_CODE_7"));
    }
    @Test
    void getPropertyNOT_EXISTING_RISK() {
        assertEquals("{NOT_EXISTING_RISK} is not supported by system!", errorCodeUtil.getProperty("NOT_EXISTING_RISK"));
    }
}