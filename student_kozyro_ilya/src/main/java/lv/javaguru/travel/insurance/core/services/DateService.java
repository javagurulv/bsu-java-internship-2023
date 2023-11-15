package lv.javaguru.travel.insurance.core.services;

import java.text.ParseException;
import java.util.Date;

public interface DateService {
    Date createDate(String date) throws ParseException;
}
