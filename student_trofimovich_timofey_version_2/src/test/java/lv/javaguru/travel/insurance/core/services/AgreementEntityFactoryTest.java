package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.SelectedRiskEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.SelectedRiskEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgreementEntityFactoryTest {
    @Mock
    private AgreementEntityRepository agreementEntityRepository;
    @Mock
    private SelectedRiskEntityRepository selectedRiskEntityRepository;
    @Mock
    private PersonEntityFactory personEntityFactory;
    @InjectMocks
    private AgreementEntityFactory agreementEntityFactory;

    @Test
    void shouldCreateAgreementEntity() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        when(agreementDTO.getPersons()).thenReturn(List.of(mock(PersonDTO.class)));
        when(personEntityFactory.createPersonEntity(any(PersonDTO.class))).thenReturn(mock(PersonEntity.class));
        AgreementEntity agreementEntity = agreementEntityFactory.createAgreementEntity(agreementDTO);
        verify(agreementEntityRepository, times(1)).save(any(AgreementEntity.class));
    }

    @Test
    void shouldReturnSelectedRisksEntities() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        when(agreementDTO.getPersons()).thenReturn(List.of(mock(PersonDTO.class)));
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("risk_1", "risk_2"));
        when(personEntityFactory.createPersonEntity(any(PersonDTO.class))).thenReturn(mock(PersonEntity.class));
        AgreementEntity agreementEntity = agreementEntityFactory.createAgreementEntity(agreementDTO);
        verify(selectedRiskEntityRepository, times(2)).save(any(SelectedRiskEntity.class));
    }
}
