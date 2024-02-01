package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class DateTimeUtil {

    public BigDecimal getDifferenceInDays(Date date1, Date date2) {
        BigDecimal difference = new BigDecimal(date2.getTime() - date1.getTime());
        difference = difference.divide(BigDecimal.valueOf(1000.0), MathContext.DECIMAL128);
        difference = difference.divide(BigDecimal.valueOf(86400.0), MathContext.DECIMAL128);

        return difference;
    }

    public Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getCurrentTime() {
        ZoneId zone = ZoneId.of("Europe/Minsk");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
        return Date.from(zonedDateTime.toInstant());
    }
}
