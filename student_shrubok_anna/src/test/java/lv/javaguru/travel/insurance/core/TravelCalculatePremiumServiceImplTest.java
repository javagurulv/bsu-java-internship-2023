package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {


    @Mock  TravelCalculatePremiumRequestValidator validator;
    @Mock DateTimeService dateTimeService;

    @InjectMocks TravelCalculatePremiumServiceImpl service;

    @Test
    void returnsResponseWithValidErrors()
    {
        TravelCalculatePremiumRequest request=mock(TravelCalculatePremiumRequest.class);
        when(validator.validate(request)).thenReturn(createErrorList());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"errorField");
        assertEquals(response.getErrors().get(0).getMessage(),"errorMessage");
    }

    @Test
    void returnsResponseWithCorrectFirstName()
    {
        TravelCalculatePremiumRequest request=mock(TravelCalculatePremiumRequest.class);
        when(validator.validate(request)).thenReturn(new ArrayList<>());
        when(request.getPersonFirstName()).thenReturn("firstName");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPersonFirstName(),"firstName");
    }

    @Test
    void returnsResponseWithCorrectLastName()
    {
        TravelCalculatePremiumRequest request=mock(TravelCalculatePremiumRequest.class);
        when(validator.validate(request)).thenReturn(new ArrayList<>());
        when(request.getPersonLastName()).thenReturn("lastName");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPersonLastName(),"lastName");
    }

    @Test
    void returnsResponseWithCorrectAgreementDateFrom()
    {
        TravelCalculatePremiumRequest request=mock(TravelCalculatePremiumRequest.class);
        when(validator.validate(request)).thenReturn(new ArrayList<>());
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getAgreementDateFrom(),createDate("14-04-2005"));
    }

    @Test
    void returnsResponseWithCorrectAgreementDateTo()
    {
        TravelCalculatePremiumRequest request=mock(TravelCalculatePremiumRequest.class);
        when(validator.validate(request)).thenReturn(new ArrayList<>());
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2005"));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getAgreementDateTo(),createDate("14-04-2005"));
    }

    @Test
    void returnsResponseWithCorrectAgreementPrice()
    {
        TravelCalculatePremiumRequest request=mock(TravelCalculatePremiumRequest.class);
        when(validator.validate(request)).thenReturn(new ArrayList<>());
        when(dateTimeService.getDaysBetween(request.getAgreementDateTo(),request.getAgreementDateFrom())).thenReturn(1L);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getAgreementPrice(), new BigDecimal(1L));
    }

    List<ValidationError> createErrorList()
    {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("errorField","errorMessage"));
        return errors;
    }

    Date createDate(String stringDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try{
            return  formatter.parse(stringDate);
        }
        catch(ParseException e)
        {
            throw new RuntimeException();
        }
    }

}