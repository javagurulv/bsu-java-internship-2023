package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class DateServiceImpl implements DateServiceUtil {

    @Override
    public Date createDate(String date, String format) throws ParseException {
        return (new SimpleDateFormat(format)).parse(date);
    }

    @Override
    public Date getTodayDate() {
        return new Date();
    }

    @Override
    public BigDecimal getDaysBetween(Date from, Date to) {
        return BigDecimal.valueOf(TimeUnit.DAYS.convert(
                to.getTime() - from.getTime(),
                TimeUnit.MILLISECONDS
        ));
    }
}
