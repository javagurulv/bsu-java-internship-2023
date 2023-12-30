
package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
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
    private ValidationErrorFactory errorFactory;
    @Mock
    private ClassifierValueRepository classifierValueRepository;
    @InjectMocks
    private CountryValidation validation;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldReturnEmptyCountryError() {
        when(agreement.getCountry()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(new ValidationErrorDTO());
        assertThat(validation.validate(agreement)).isPresent();
    }

    @Test
    void shouldReturnNullCountryError() {
        when(agreement.getCountry()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(new ValidationErrorDTO());
        assertThat(validation.validate(agreement)).isPresent();
    }

    @Test
    void shouldNotReturnError() {
        when(agreement.getCountry()).thenReturn("LATVIA");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "LATVIA")).thenReturn(Optional.of(new ClassifierValue()));
        assertThat(validation.validate(agreement)).isEmpty();
    }

    @Test
    void shouldReturnErrorWhenTravelMedicalIsNotChosen() {
        when(agreement.getCountry()).thenReturn("LATVIA");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "LATVIA")).thenReturn(Optional.of(new ClassifierValue()));
        assertThat(validation.validate(agreement)).isEmpty();
    }
}

