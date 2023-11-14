package lv.javaguru.travel.insurance.core.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateServiceImpl implements DateService{

    DateFormat dateFormat;

    public DateServiceImpl(
            @Value("yyyy-MM-dd")
            String dateStringFormat
    ) {
        dateFormat = new SimpleDateFormat(dateStringFormat);
    }
    @Override
    public Date createDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }
}
