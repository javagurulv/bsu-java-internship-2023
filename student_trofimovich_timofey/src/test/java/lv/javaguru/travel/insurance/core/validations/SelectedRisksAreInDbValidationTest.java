package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRisksAreInDbValidationTest {
    @Mock
    ValidationErrorFactory factory;
    @Mock
    ClassifierValueRepository classifierValueRepository;
    @InjectMocks SelectedRisksAreInDbValidation validation;

    /*@Test
    public void shouldReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("DUMMY_RISK"));
        List<ValidationError> errors = validation.validateList(request);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_9");
        assertThat(errors.get(0).getDescription()).isEqualTo("Selected risk is invalid!");
    }*/
}
