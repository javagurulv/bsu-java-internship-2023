package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelUuidValidator;
import org.aspectj.util.Reflection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelGetPolicyServiceImplTest {
    @InjectMocks
    private TravelGetPolicyServiceImpl service;
    @Mock
    private TravelUuidValidator travelUuidValidator;
    @Mock
    private ResponseOnEntityBuilder responseOnEntityBuilder;

    @Test
    public void getResultWithoutErrorsTest() {
        String uuid = "UUID";
        TravelGetPolicyCoreResult result = mock(TravelGetPolicyCoreResult.class);
        when(travelUuidValidator.validate(uuid)).thenReturn(List.of());
        ReflectionTestUtils.setField(service, "travelUuidValidator", travelUuidValidator);
        when(responseOnEntityBuilder.buildResponse(uuid)).thenReturn(result);
        TravelGetPolicyCoreCommand command = mock(TravelGetPolicyCoreCommand.class);
        when(command.getUuid()).thenReturn(uuid);
        assertEquals(service.getPolicy(command), result);
    }

    @Test
    public void getResultWithErrorsTest() {
        String uuid = "UUID";
        TravelGetPolicyCoreCommand command = mock(TravelGetPolicyCoreCommand.class);
        when(command.getUuid()).thenReturn(uuid);
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(travelUuidValidator.validate(uuid)).thenReturn(List.of(error));
        TravelGetPolicyCoreResult result = service.getPolicy(command);
        assertTrue(result.hasErrors());
        assertEquals(result.getErrors().size(), 1);
        assertEquals(result.getErrors().get(0), error);

    }
}
