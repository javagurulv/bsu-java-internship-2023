package lv.javaguru.travel.insurance.core.service;

import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.util.ErrorFileLoaderUtil;
import lv.javaguru.travel.insurance.dto.Placer;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {

    @Mock
    ErrorFileLoaderUtil errorFileLoaderUtil;

    @InjectMocks
    ValidationErrorFactory validationErrorFactory;

    String code = "code";

    @Test
    void testSimpleDescription() {
        when(errorFileLoaderUtil.getErrorDescription(code)).thenReturn("description");

        var error = validationErrorFactory.buildError(code);

        assertEquals(new ValidationError(code, "description"), error);
    }

    @Test
    void testPlacerDescription() {
        Placer placer = new Placer("something", "1");
        String trueDescription = "description 1";
        when(errorFileLoaderUtil.getErrorDescription(code)).thenReturn("description ${something}");

        var error = validationErrorFactory.buildError(code, placer);

        assertEquals(new ValidationError(code, trueDescription), error);
    }

    @Test
    void testMultiPlacerDescription() {
        List<Placer> placers = List.of(
                new Placer("something1", "1"),
                new Placer("something2", "2")
        );
        String trueDescription = "description 1 2";
        when(errorFileLoaderUtil.getErrorDescription(code)).thenReturn("description ${something1} ${something2}");

        var error = validationErrorFactory.buildError(code, placers);

        assertEquals(new ValidationError(code, trueDescription), error);
    }

    @Test
    void testMultiPlacerDescriptionBetweenText() {
        List<Placer> placers = List.of(
                new Placer("something1", "1"),
                new Placer("something2", "2")
        );
        String trueDescription = "desc1ripti2on";
        when(errorFileLoaderUtil.getErrorDescription(code)).thenReturn("desc${something1}ripti${something2}on");

        var error = validationErrorFactory.buildError(code, placers);

        assertEquals(new ValidationError(code, trueDescription), error);
    }


}
