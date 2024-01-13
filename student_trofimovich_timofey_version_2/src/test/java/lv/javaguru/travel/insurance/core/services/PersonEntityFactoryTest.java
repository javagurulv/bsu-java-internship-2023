package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.PersonEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonEntityFactoryTest {
    @Mock
    private PersonEntityRepository repository;
    @InjectMocks
    private PersonEntityFactory personEntityFactory;

    @Test
    void shouldNotSaveIfPersonAlreadyExists() {
        when(repository.findBy("first_name", "last_name", "personCode"))
                .thenReturn(Optional.of(mock(PersonEntity.class)));
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonFirstName()).thenReturn("first_name");
        when(personDTO.getPersonLastName()).thenReturn("last_name");
        when(personDTO.getPersonUUID()).thenReturn("personCode");
        personEntityFactory.createPersonEntity(personDTO);
        verify(repository, never()).save(any(PersonEntity.class));
    }

    @Test
    void shouldSavePersonIfItDoesNotExists() {
        when(repository.findBy("first_name", "last_name", "personCode"))
                .thenReturn(Optional.empty());
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonFirstName()).thenReturn("first_name");
        when(personDTO.getPersonLastName()).thenReturn("last_name");
        when(personDTO.getPersonUUID()).thenReturn("personCode");
        when(repository.save(any(PersonEntity.class))).thenReturn(mock(PersonEntity.class));
        personEntityFactory.createPersonEntity(personDTO);
        verify(repository, times(1)).save(any(PersonEntity.class));
    }
}
