package lv.javaguru.travel.insurance.rest.controller_v2_test.without_all_fields;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2RequestWithoutFieldsTest extends TravelCalculatePremiumControllerV2TestCase {

    //all fields not provided, but selected risk is TRAVEL_MEDICAL
    @Test
    public void execute19() throws Exception {
        executeAndCompare("all fields not provided TM");
    }

    //all fields not provided
    @Test
    public void execute20() throws Exception {
        executeAndCompare("all fields not provided");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "without_all_fields/";
    }
}
