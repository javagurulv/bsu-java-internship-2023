package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TravelUnderwritingImplTest {
        @InjectMocks
        TravelUnderwriting underwriting = new TravelUnderwritingImpl();
        @Mock
        SelectedRisksPremiumCalculator calculator;

        @Mock
        private AgreementDTO agreement;

        @Mock
        private PersonDTO person;

        @Test
        public void calculatePremiumTest() {

            List<String> risks = new ArrayList<>();
            risks.add("TRAVEL_MEDICAL");


            when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2022-09-12"));
            when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2022-09-11"));
            when(agreement.getSelectedRisks()).thenReturn(risks);

            List<RiskDTO> risksPremium = new ArrayList<>();
            risksPremium.add(new RiskDTO("TRAVEL_MEDICAL", BigDecimal.valueOf(1)));
            when(calculator.calculatePremiumForAllRisks(agreement, person)).thenReturn(risksPremium);

            assertEquals(BigDecimal.valueOf(1),underwriting.calculatePremium(agreement, person));
        }
    }
