
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryValidationTest {
    @Mock ValidationErrorFactory errorFactory;
    @Mock
    ClassifierValueRepository classifierValueRepository;
    @InjectMocks CountryValidation validation;
    @Test
    void shouldReturnEmptyCountryError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }
    @Test
    void shouldReturnNullCountryError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn(null);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("LATVIA");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "LATVIA")).thenReturn(Optional.of(new ClassifierValue()));
        assertThat(validation.validate(request)).isEmpty();
    }

    @Test
    void shouldNotReturnErrorWhenTravelMedicalIsNotChosen() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        assertThat(validation.validate(request)).isEmpty();
    }
}

