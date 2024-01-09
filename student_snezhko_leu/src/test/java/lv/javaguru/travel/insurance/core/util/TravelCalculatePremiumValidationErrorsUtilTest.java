package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumValidationErrorsUtilTest {
   ValidationErrorsUtil util;

   @BeforeEach
    public void initUtil() {
       try {
           util = new ValidationErrorsUtil();
       }
       catch (IOException e) {
           util = null;
       }
//       return util;
   }

   @Test
   public void ValidationErrorsUtilIsNotNull() {
        //initUtil();
        assertNotNull(util);
   }

   @Test
   public void ValidationErrorsUtilGetCorrectDescriptionByErrorCodeTest() {
        String errorCode = "ERROR_CODE_1";
        String description = "Field personFirstName is empty!";
        if (util != null) {
            String descriptionFromFile = util.getDescriptionByErrorCode(errorCode);
            assertEquals(description, descriptionFromFile);
        }
   }

   @Test
   public void ErrorsUtilGetCorrectDescriptionWithPlaceholderTest() {
        if (util != null) {
            String errorCode = "ERROR_CODE_9";
            String risk = "UNCORRECT_RISK";
            String description = "Risk with ic = UNCORRECT_RISK is not supported!";
            Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", risk);
            List<Placeholder> placeholderList = new ArrayList<>();
            placeholderList.add(placeholder);
            String descriptionFromFile = util.getErrorDescription(errorCode, placeholderList);
            assertEquals(description, descriptionFromFile);
        }
   }

    @Test
    public void ErrorsUtilGetDescriptionWithTwoPlaceholdersTest() {
        if (util != null) {
            String errorCode = "ERROR_CODE_Test";
            String risk1 = "TEST_PLACEHOLDER_1";
            String risk2 = "TEST_PLACEHOLDER_2";
            String description = "Placeholder1 = " + risk1 + " and Placeholder2 = " + risk2 + "!";
            Placeholder placeholder1 = new Placeholder("PLACEHOLDER_1", risk1);
            Placeholder placeholder2 = new Placeholder("PLACEHOLDER_2", risk2);

            List<Placeholder> placeholderList = new ArrayList<>();
            placeholderList.add(placeholder1);
            placeholderList.add(placeholder2);

            String descriptionFromFile = util.getErrorDescription(errorCode, placeholderList);
            assertEquals(description, descriptionFromFile);
        }
    }
}
