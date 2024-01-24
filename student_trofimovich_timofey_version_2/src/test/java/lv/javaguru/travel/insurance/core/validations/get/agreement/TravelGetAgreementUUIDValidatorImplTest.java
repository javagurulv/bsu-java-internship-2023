package lv.javaguru.travel.insurance.core.validations.get.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelGetAgreementUUIDValidatorImplTest {
    @Mock
    private AgreementEntityRepository repository;
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private TravelGetAgreementUUIDValidatorImpl validator;

    @Test
    void shouldReturnErrorWhenUuidIsIncorrect() {
        when(repository.findByUuid("uuid")).thenReturn(Optional.empty());
        ValidationErrorDTO validationErrorDTO = mock(ValidationErrorDTO.class);
        when(factory.buildError(eq("ERROR_CODE_18"), anyList())).thenReturn(validationErrorDTO);
        assertThat(validator.validate("uuid")).isPresent();
    }
}
