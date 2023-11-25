package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AgeCoefficientRepositoryTest {
    @Autowired private AgeCoefficientRepository ageCoefficientRepository;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientRepository);
    }
    @Test
    public void shouldFindAgeCoefficientFrom0To5() {
        testValueByAge(5);
    }
    @Test
    public void shouldFindAgeCoefficientFrom6To10() {
        testValueByAge(6);
    }
    @Test
    public void shouldFindAgeCoefficientFrom11To17() {
        testValueByAge(12);
    }
    @Test
    public void shouldFindAgeCoefficientFrom18To40() {
        testValueByAge(33);
    }
    @Test
    public void shouldFindAgeCoefficientFrom41To65() {
        testValueByAge(65);
    }
    @Test
    public void shouldFindAgeCoefficientFrom66To150() {
        testValueByAge(140);
    }



    @Test
    public void shouldNotFindAgeMoreThanExist() {
        Optional<AgeCoefficient> valueOpt = ageCoefficientRepository.findByAge(200);
        assertTrue(valueOpt.isEmpty());
    }
    @Test
    public void shouldNotFindAgeLessThanExist() {
        Optional<AgeCoefficient> valueOpt = ageCoefficientRepository.findByAge(-2);
        assertTrue(valueOpt.isEmpty());
    }
    public void testValueByAge(int age){
        Optional<AgeCoefficient> valueOpt = ageCoefficientRepository.findByAge(age);
        assertTrue(valueOpt.isPresent());
        assertTrue(valueOpt.get().getAgeFrom() <= age);
        assertTrue(valueOpt.get().getAgeTo() >= age);
    }
}
