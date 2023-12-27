package lv.javaguru.travel.insurance.core.service;

import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.util.ErrorFileLoaderUtil;
import lv.javaguru.travel.insurance.dto.Placeholder;
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
        Placeholder placeholder = new Placeholder("something", "1");
        String trueDescription = "description 1";
        when(errorFileLoaderUtil.getErrorDescription(code)).thenReturn("description ${something}");

        var error = validationErrorFactory.buildError(code, placeholder);

        assertEquals(new ValidationError(code, trueDescription), error);
    }

    @Test
    void testMultiPlacerDescription() {
        List<Placeholder> placeholders = List.of(
                new Placeholder("something1", "1"),
                new Placeholder("something2", "2")
        );
        String trueDescription = "description 1 2";
        when(errorFileLoaderUtil.getErrorDescription(code)).thenReturn("description ${something1} ${something2}");

        var error = validationErrorFactory.buildError(code, placeholders);

        assertEquals(new ValidationError(code, trueDescription), error);
    }

    @Test
    void testMultiPlacerDescriptionBetweenText() {
        List<Placeholder> placeholders = List.of(
                new Placeholder("something1", "1"),
                new Placeholder("something2", "2")
        );
        String trueDescription = "desc1ripti2on";
        when(errorFileLoaderUtil.getErrorDescription(code)).thenReturn("desc${something1}ripti${something2}on");

        var error = validationErrorFactory.buildError(code, placeholders);

        assertEquals(new ValidationError(code, trueDescription), error);
    }


}
