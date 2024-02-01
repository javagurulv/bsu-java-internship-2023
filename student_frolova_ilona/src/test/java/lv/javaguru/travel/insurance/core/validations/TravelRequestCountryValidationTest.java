package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestCountryValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationErrorFactory errorFactory;

    @Mock
    private ClassifierValueRepository repository;

    @InjectMocks
    private TravelRequestCountryValidation validation;

    @Test
    public void returnNothingIfCountryIsNull() {
        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void returnErrorIfCountryIsNotRegistered() {
        when(request.getCountry()).thenReturn("SMTH");
        when(errorFactory.buildError(any(), any())).thenReturn(new ValidationError());
        when(repository.findByClassifierTitleAndIc(any(), any())).thenReturn(Optional.empty());

        Optional<ValidationError> expected = Optional.of(new ValidationError());
        Optional<ValidationError> error = validation.check(request);

        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfCountryIsRegistered() {
        when(request.getCountry()).thenReturn("JAPAN");
        when(repository.findByClassifierTitleAndIc(any(), any())).thenReturn(
                Optional.of(mock(ClassifierValue.class))
        );

        Optional<ValidationError> error = validation.check(request);

        assertEquals(Optional.empty(), error);
    }
}
