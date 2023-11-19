package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldReturnErrorwhenPersonFirstNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1); // Это утверждение проверяет, что список errors не является пустым. Если список errors пустой, тест не пройдет, так как предполагается, что должна быть хотя бы одна ошибка.
        assertEquals(errors.get(0).getField(), "personFirstName"); // Это утверждение проверяет, что размер списка errors равен 1. То есть, ожидается, что только одна ошибка присутствует в списке.
        assertEquals(errors.get(0).getMessage(), "Must not be empty!"); // Это утверждение проверяет, что сообщение об ошибке (message) у первой ошибки в списке равно строке "Must not be empty!". То есть, тест предполагает, что сообщение об ошибке указывает на то, что поле personFirstName не должно быть пустым.
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty()); // Это утверждение проверяет, что список errors является пустым. Если список errors пустой, тест пройдет, так как предполагается, что ошибок быть не должно.
    }
}
