package lv.javaguru.travel.insurance.core.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class DateServiceImpl implements DateService{

    DateFormat dateFormat;

    @Override
    public Date createDate(String date, String format) throws ParseException {
        return (new SimpleDateFormat(format)).parse(date);
    }

    @Override
    public BigDecimal getDaysBetween(Date from, Date to) {
        return BigDecimal.valueOf(TimeUnit.DAYS.convert(
                to.getTime() - from.getTime(),
                TimeUnit.MILLISECONDS
        ));
    }
}
