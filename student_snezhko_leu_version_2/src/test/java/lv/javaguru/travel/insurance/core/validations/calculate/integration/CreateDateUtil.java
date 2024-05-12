package lv.javaguru.travel.insurance.core.validations.calculate.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateDateUtil {
    public static Date createDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }
        catch(ParseException e) {
            throw new RuntimeException();
        }
    }
}
