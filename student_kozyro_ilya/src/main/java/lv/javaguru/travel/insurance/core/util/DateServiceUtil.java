package lv.javaguru.travel.insurance.core.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public interface DateServiceUtil {
    Date createDate(String date, String format) throws ParseException;

    Date getTodayDate();
    BigDecimal getDaysBetween(Date from, Date to);
}
