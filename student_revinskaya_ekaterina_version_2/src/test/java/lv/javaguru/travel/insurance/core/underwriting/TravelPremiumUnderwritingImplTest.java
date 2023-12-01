package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {
    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwriting;

    @Test
    public void rightCalculateUnderwriting() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        SelectedRisksPremiumCalculator selectedRisksPremiumCalculator = mock(SelectedRisksPremiumCalculator.class);
        when(selectedRisksPremiumCalculator.calculateSelectedRisksPremium(agreementDTO, personDTO))
                .thenReturn(List.of(new RiskDTO("RISK_1", BigDecimal.valueOf(4)),
                        new RiskDTO("RISK_2", BigDecimal.valueOf(2))));
        ReflectionTestUtils.setField(travelPremiumUnderwriting, "selectedRisksPremiumCalculator", selectedRisksPremiumCalculator);
        assertEquals(travelPremiumUnderwriting.calculatePremium(agreementDTO, personDTO).getTotalPremium(), BigDecimal.valueOf(6));
        assertEquals(travelPremiumUnderwriting.calculatePremium(agreementDTO, personDTO).getRisks().size(), 2);

    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
