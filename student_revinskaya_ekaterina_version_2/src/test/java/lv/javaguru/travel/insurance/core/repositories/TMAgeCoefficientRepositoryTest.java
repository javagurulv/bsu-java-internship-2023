package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMAgeCoefficient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TMAgeCoefficientRepositoryTest {
    @Autowired
    private TMAgeCoefficientRepository TMAgeCoefficientRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(TMAgeCoefficientRepository);
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
        Optional<TMAgeCoefficient> valueOpt = TMAgeCoefficientRepository.findByAge(200);
        assertTrue(valueOpt.isEmpty());
    }

    @Test
    public void shouldNotFindAgeLessThanExist() {
        Optional<TMAgeCoefficient> valueOpt = TMAgeCoefficientRepository.findByAge(-2);
        assertTrue(valueOpt.isEmpty());
    }

    public void testValueByAge(int age) {
        Optional<TMAgeCoefficient> valueOpt = TMAgeCoefficientRepository.findByAge(age);
        assertTrue(valueOpt.isPresent());
        assertTrue(valueOpt.get().getAgeFrom() <= age);
        assertTrue(valueOpt.get().getAgeTo() >= age);
    }
}
