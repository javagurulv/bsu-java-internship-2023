package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AgeCoefficientRepositoryTest {
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientRepository);
    }
    @Test
    void findFive() {
        shouldFindByIc(5, BigDecimal.valueOf(1.10));
    }
    @Test
    void findEighteen() {
        shouldFindByIc(18, BigDecimal.valueOf(1.10));
    }
    @Test
    void findEighty() {
        shouldFindByIc(80, BigDecimal.valueOf(1.50));
    }
    @Test
    public void findFake() {
        Optional<AgeCoefficient> valueOpt = ageCoefficientRepository.findByAge(200);
        assertTrue(valueOpt.isEmpty());
    }
    private void shouldFindByIc(int age, BigDecimal coefficient) {
        Optional<AgeCoefficient> valueOptional = ageCoefficientRepository.findByAge(age);
        assertTrue(valueOptional.isPresent());
        assertEquals(coefficient.setScale(2), valueOptional.get().getCoefficient());
    }
}