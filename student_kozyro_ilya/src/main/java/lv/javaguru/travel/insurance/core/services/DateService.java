package lv.javaguru.travel.insurance.core.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public interface DateService {
    Date createDate(String date, String format) throws ParseException;
    BigDecimal getDaysBetween(Date from, Date to);
}
