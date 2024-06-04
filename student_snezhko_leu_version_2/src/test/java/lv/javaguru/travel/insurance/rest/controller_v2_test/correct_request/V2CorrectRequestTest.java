package lv.javaguru.travel.insurance.rest.controller_v2_test.correct_request;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2CorrectRequestTest extends TravelCalculatePremiumControllerV2TestCase {
    //All fields filled. Selected risk - TRAVEL_MEDICAL
    @Test
    public void execute1() throws Exception {
        executeAndCompare("correct request TM");
    }

    //All fields filled. Selected risk - TRAVEL_CANCELLATION
    @Test
    public void execute24() throws Exception {
        executeAndCompare("correct request TC");
    }

    //All fields filled. Selected risks: TRAVEL_CANCELLATION, TRAVEL_MEDICAL
    @Test
    public void execute25() throws Exception {
        executeAndCompare("correct request TM, TC");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "correct_request/";
    }
}
