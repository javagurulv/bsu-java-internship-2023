package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.junit.platform.commons.util.FunctionUtils.where;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class CalculateAgeUtilTest {
    @InjectMocks
    private CalculateAgeUtil calculateAgeUtil;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @Test
    public void calculateAge() {
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(new java.util.Date(Date.valueOf("2006-01-01").getTime()));
        Integer age = calculateAgeUtil.calculateAge(new java.util.Date(Date.valueOf("2000-01-01").getTime()));
        assertEquals("", 6, age);
    }
}
