
package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryValidationTest {
    @Mock
    ValidationErrorFactory errorFactory;
    @Mock
    ClassifierValueRepository classifierValueRepository;
    @InjectMocks
    CountryValidation validation;
    @Test
    void shouldReturnEmptyCountryError() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }
    @Test
    void shouldReturnNullCountryError() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("LATVIA");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "LATVIA")).thenReturn(Optional.of(new ClassifierValue()));
        assertThat(validation.validate(request)).isEmpty();
    }

    @Test
    void shouldReturnErrorWhenTravelMedicalIsNotChosen() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("LATVIA");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "LATVIA")).thenReturn(Optional.of(new ClassifierValue()));
        assertThat(validation.validate(request)).isEmpty();
    }
}

