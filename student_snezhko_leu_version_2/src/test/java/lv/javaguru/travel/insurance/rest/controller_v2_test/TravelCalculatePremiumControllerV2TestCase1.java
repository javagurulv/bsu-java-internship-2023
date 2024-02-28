package lv.javaguru.travel.insurance.rest.controller_v2_test;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerV2TestCase1 extends TravelCalculatePremiumControllerV2TestCase{
        @Test
        public void execute() throws Exception {
            executeAndCompare();
        }

        @Override
        protected String getTestCaseFolderName() {
            return "test_case_1";
        }
}
