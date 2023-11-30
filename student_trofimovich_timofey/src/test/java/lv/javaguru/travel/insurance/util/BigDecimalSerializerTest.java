package lv.javaguru.travel.insurance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JsonTest
@ExtendWith(SpringExtension.class)
public class BigDecimalSerializerTest {
    @Autowired
    ObjectMapper mapper;

    @Test
    void testSerialization() throws JsonProcessingException {
        BigDecimal value = new BigDecimal("7.5");
        String json = mapper.writeValueAsString(value);
        assertThat(json).isEqualTo("7.50");
    }
}
